package com.eicon.Infuse.pedido.entity;

import com.eicon.Infuse.cliente.entity.Cliente;
import com.eicon.Infuse.comum.Entidade;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_pedido")
public class Pedido extends Entidade implements Serializable {

    @JoinColumn(name = "cliente_id")
    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    @Column(name = "valor_pedido", precision = 10, scale = 2, nullable = true)
    private BigDecimal valorPedido;

    @Column(name = "valor_com_desconto", precision = 10, scale = 2)
    private BigDecimal valorComDesconto;

    @Column(name = "desconto", precision = 10, scale = 2)
    private BigDecimal desconto;

    public Pedido(Long id, Cliente cliente, BigDecimal valorPedido, BigDecimal valorComDesconto, BigDecimal desconto) {
        this.setId(id);
        this.setCliente(cliente);
        this.setValorPedido(valorPedido);
        this.setValorComDesconto(valorComDesconto);
        this.setDesconto(desconto);
    }
}
