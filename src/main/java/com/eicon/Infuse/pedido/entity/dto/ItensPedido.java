package com.eicon.Infuse.pedido.entity.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView({PedidoRequest.PedidoView.Post.class, PedidoRequest.PedidoView.Put.class})
    private Long produtoId;

    @JsonView({PedidoRequest.PedidoView.Post.class, PedidoRequest.PedidoView.Put.class})
    private Long pedidoId;

    @JsonView({PedidoRequest.PedidoView.Post.class, PedidoRequest.PedidoView.Put.class})
    private BigDecimal valorUnitario;

    @JsonView({PedidoRequest.PedidoView.Post.class, PedidoRequest.PedidoView.Put.class})
    private Integer quantidade;

    private BigDecimal valorTotal;

    public void add(BigDecimal valorUnitario, Integer quantidade) {
        valorTotal = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }


}
