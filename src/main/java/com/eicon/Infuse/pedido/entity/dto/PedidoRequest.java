package com.eicon.Infuse.pedido.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface PedidoView {
        public static interface Post {}
        public static interface Put {}
    }

    private Long id;

    @JsonView({PedidoView.Post.class, PedidoView.Put.class})
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @JsonView({PedidoView.Post.class, PedidoView.Put.class})
    @Column(precision = 10, scale = 2)
    private BigDecimal valorPedido;

    @JsonView({PedidoView.Post.class, PedidoView.Put.class})
    private Long clienteId;

    @JsonView({PedidoView.Post.class, PedidoView.Put.class})
    private Set<ItensPedido> itens;

    private BigDecimal valorComDesconto;

    public PedidoRequest(LocalDate createdAt, BigDecimal valorPedido, Long clienteId, BigDecimal valorComDesconto, Set<ItensPedido> itens){
        this.createdAt = createdAt;
        this.valorPedido = valorPedido;
        this.clienteId = clienteId;
        this.valorComDesconto = valorComDesconto;
        this.itens = itens;
    }
}
