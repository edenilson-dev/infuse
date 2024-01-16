package com.eicon.Infuse.comum;

import com.eicon.Infuse.pedido.entity.ProdutoPedidoAssociacao;
import com.eicon.Infuse.produto.entity.dto.ProdutoDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement
public class ItensPedidoResponse implements Serializable {

    private ProdutoDto produto;

    private BigDecimal valorUnitario;

    private Integer quantidade;

    public static ItensPedidoResponse to(ProdutoPedidoAssociacao produtoPedidoAssociacao) {
        return ItensPedidoResponse.builder()
                .produto(ProdutoDto.to(produtoPedidoAssociacao.getProduto()))
                .valorUnitario(produtoPedidoAssociacao.getValorUnitario())
                .quantidade(produtoPedidoAssociacao.getQuantidade())
                .build();
    }
}
