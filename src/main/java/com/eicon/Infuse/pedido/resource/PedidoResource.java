package com.eicon.Infuse.pedido.resource;

import com.eicon.Infuse.comum.filtro.PedidoFiltro;
import com.eicon.Infuse.pedido.entity.dto.PedidoRequest;
import com.eicon.Infuse.pedido.entity.dto.PedidoRespone;
import com.eicon.Infuse.pedido.service.PedidoService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/pedidos")
public class PedidoResource {

    @Autowired
   private PedidoService pedidoService;

    @PostMapping()
    public ResponseEntity<PedidoRespone> save(
            @RequestBody
            @JsonView({PedidoRequest.PedidoView.Post.class})
            PedidoRequest pedidoDto) {
        log.info("Passando pelo Controller Save Pedido received {} ", pedidoDto.toString());
        return ResponseEntity.ok().body(pedidoService.save(pedidoDto));
    }

    @GetMapping()
    public ResponseEntity<Page<PedidoRespone>> findAllFiltro(PedidoFiltro filtro, Pageable pageable) {
        return ResponseEntity.ok().body(pedidoService.findByAll(filtro, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoRespone> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(pedidoService.findById(id));
    }
}
