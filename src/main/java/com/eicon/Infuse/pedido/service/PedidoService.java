package com.eicon.Infuse.pedido.service;

import com.eicon.Infuse.comum.filtro.PedidoFiltro;
import com.eicon.Infuse.pedido.entity.dto.PedidoRequest;
import com.eicon.Infuse.pedido.entity.dto.PedidoRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    PedidoRespone save(PedidoRequest pedidoDto);

    PedidoRespone findById(Long id);

    PedidoRequest update(PedidoRequest pedidoDto);

    Page<PedidoRespone> findByAll(PedidoFiltro filtro, Pageable pageable);




}
