package com.eicon.Infuse.pedido.entity.dto;

import com.eicon.Infuse.cliente.entity.dto.ClienteDto;
import com.eicon.Infuse.comum.ItensPedidoResponse;
import com.eicon.Infuse.pedido.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement
public class PedidoRespone extends RepresentationModel<PedidoRespone> implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface PedidoResponeView {
        public static interface Post {}
        public static interface Put {}
    }

    private Long id;

    @JsonView({PedidoResponeView.Post.class, PedidoResponeView.Put.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonView({PedidoResponeView.Post.class, PedidoResponeView.Put.class})
    @Column(precision = 10, scale = 2)
    private BigDecimal valorPedido;

    @JsonView({PedidoResponeView.Post.class, PedidoResponeView.Put.class})
    private ClienteDto cliente;

    @JsonView({PedidoResponeView.Post.class, PedidoResponeView.Put.class})
    private List<ItensPedidoResponse> itens;

    private BigDecimal valorComDesconto;

    private BigDecimal desconto;

    public static PedidoRespone to(Pedido pedido) {
        return PedidoRespone.builder()
                .id(pedido.getId())
                .createdAt(pedido.getCreatedAt())
                .valorPedido(pedido.getValorPedido())
                .cliente(ClienteDto.to(pedido.getCliente()))
                .desconto(pedido.getDesconto())
                .valorComDesconto(pedido.getValorComDesconto())
                .build();
    }
}
