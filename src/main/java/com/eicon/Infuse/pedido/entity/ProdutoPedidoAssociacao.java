package com.eicon.Infuse.pedido.entity;

import com.eicon.Infuse.produto.entity.Produto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "rl_produto_pedido")
public class ProdutoPedidoAssociacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @EmbeddedId
    private ProdutoPedidoId id;

    @MapsId("produto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false)
    private Produto produto;

    @MapsId("pedido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false)
    private Pedido pedido;

    @Column(name = "valor_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}
