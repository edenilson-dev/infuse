package com.eicon.Infuse.pedido.service.impl;

import com.eicon.Infuse.cliente.ClientNotFoundException;
import com.eicon.Infuse.cliente.repository.ClienteRepository;
import com.eicon.Infuse.comum.ItensPedidoResponse;
import com.eicon.Infuse.comum.filtro.PedidoFiltro;
import com.eicon.Infuse.pedido.entity.Pedido;
import com.eicon.Infuse.pedido.entity.ProdutoPedidoAssociacao;
import com.eicon.Infuse.pedido.entity.ProdutoPedidoId;
import com.eicon.Infuse.pedido.entity.dto.ItensPedido;
import com.eicon.Infuse.pedido.entity.dto.PedidoRequest;
import com.eicon.Infuse.pedido.entity.dto.PedidoRespone;
import com.eicon.Infuse.pedido.repository.PedidoRepository;
import com.eicon.Infuse.pedido.repository.ProdutoPedidoAssociacaoRepository;
import com.eicon.Infuse.pedido.resource.PedidoResource;
import com.eicon.Infuse.pedido.service.PedidoService;
import com.eicon.Infuse.pedido.util.PedidoUtil;
import com.eicon.Infuse.produto.entity.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final ProdutoPedidoAssociacaoRepository produtoPedidoAssociacaoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    @Value("${percentual.cinco}")
    private String cincoPorcento;

    @Value("${percentual.dez}")
    private String dezPorcento;
    @Override
    public PedidoRespone save(PedidoRequest pedidoDto) {
        var cliente = clienteRepository.findById(pedidoDto.getClienteId()).orElseThrow(ClientNotFoundException::new);

        var pedido = new Pedido();

        if(!pedidoDto.getItens().isEmpty()) pedidoDto.getItens().forEach(getValorTotalItem());

        pedidoDto.setValorPedido(pedidoDto.getItens().stream().map(ItensPedido::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add));

        BeanUtils.copyProperties(pedidoDto, pedido);
        pedido.setCliente(cliente);

        if(pedido.getCreatedAt() == null) pedido.setCreatedAt(LocalDate.now());

        if(!pedidoDto.getItens().isEmpty() && pedidoDto.getItens().size() >= 5){
            PedidoUtil.aplicaDescontoCinco(pedidoDto, pedido, new BigDecimal(cincoPorcento));
            log.info("Aplicando 5% de desconto do valor > " + pedidoDto.getValorPedido());
        }
        if(!pedidoDto.getItens().isEmpty() && pedidoDto.getItens().size() >= 10){
            PedidoUtil.aplicaDescontoDez(pedidoDto, pedido, new BigDecimal(dezPorcento));
            log.info("Aplicando 10% de desconto do valor > " + pedidoDto.getValorPedido());
        }

        var pedidoSave = pedidoRepository.save(pedido);

        List<ProdutoPedidoAssociacao> list = new ArrayList<>();
        if(!pedidoDto.getItens().isEmpty()) {
            pedidoDto.getItens().forEach(i -> {
                var produtoPedidoAssociado = new ProdutoPedidoAssociacao();
                var produto = new Produto();
                produto.setId(i.getProdutoId());

                produtoPedidoAssociado.setPedido(pedidoSave);
                produtoPedidoAssociado.setProduto(produto);
                produtoPedidoAssociado.setValorUnitario(i.getValorUnitario());
                produtoPedidoAssociado.setQuantidade(i.getQuantidade());
                produtoPedidoAssociado.setId(new ProdutoPedidoId(i.getProdutoId(), pedidoSave.getId()));
                list.add(produtoPedidoAssociado);
            });
        }

        produtoPedidoAssociacaoRepository.saveAll(list);
        var pedidoResponse = PedidoRespone.to(pedidoSave);
        consultarItens(pedidoResponse);

        return pedidoResponse;
    }

    @Override
    public PedidoRespone findById(Long id) {
        var resul = PedidoRespone.to(pedidoRepository.findById(id).get());
        consultarItens(resul);
        return resul;
    }

    @Override
    public PedidoRequest update(PedidoRequest pedidoDto) {
        return null;
    }

    @Override
    public Page<PedidoRespone> findByAll(PedidoFiltro filtro, Pageable pageable) {
        var r = pedidoRepository.findAll(filtro.toSpec(), pageable);

        final Page<PedidoRespone> res = r.map(PedidoRespone::to);
        res.forEach(a -> {
            consultarItens(a);
            a.add(linkTo(methodOn(PedidoResource.class).findById(a.getId())).withSelfRel());
        });
        return res;
    }

    private void consultarItens(PedidoRespone a) {
        List<ItensPedidoResponse> list = new ArrayList<>();
        var result = produtoPedidoAssociacaoRepository.findAllIdPedido(a.getId());
        if(!result.isEmpty()) list.addAll(result.stream().map(ItensPedidoResponse::to).collect(Collectors.toList()));
        a.setItens(list);
    }

    private static Consumer<ItensPedido> getValorTotalItem() {
        return a -> {
            if(a.getQuantidade() == null || a.getQuantidade() == 0) a.setQuantidade(1);
            a.add(a.getValorUnitario(), a.getQuantidade());
        };
    }
}
