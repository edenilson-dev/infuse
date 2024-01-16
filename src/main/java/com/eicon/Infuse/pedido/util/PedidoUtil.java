package com.eicon.Infuse.pedido.util;

import com.eicon.Infuse.pedido.entity.Pedido;
import com.eicon.Infuse.pedido.entity.dto.PedidoRequest;

import java.math.BigDecimal;

public class PedidoUtil {

    public static void aplicaDescontoCinco(PedidoRequest pedidoDto, Pedido pedido, BigDecimal cincoPorcento) {
        var vlrDesconto = pedidoDto.getValorPedido().multiply((cincoPorcento));
        pedidoDto.setValorComDesconto(pedidoDto.getValorPedido().subtract(vlrDesconto));
        pedido.setValorComDesconto(pedidoDto.getValorComDesconto());
        pedido.setDesconto(cincoPorcento);
    }

    public static void aplicaDescontoDez(PedidoRequest pedidoDto, Pedido pedido, BigDecimal dezPorcento) {
        var vlrDesconto = pedidoDto.getValorPedido().multiply(dezPorcento);
        pedidoDto.setValorComDesconto(pedidoDto.getValorPedido().subtract(vlrDesconto));
        pedido.setDesconto(dezPorcento);
        pedido.setValorComDesconto(pedidoDto.getValorComDesconto());
    }
}
