package com.eicon.Infuse.pedido.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class ProdutoPedidoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "produto_id")
    private Long produto;

    @Column	(name = "pedido_id")
    private Long pedido;

}
