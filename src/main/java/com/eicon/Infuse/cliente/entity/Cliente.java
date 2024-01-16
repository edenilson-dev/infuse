package com.eicon.Infuse.cliente.entity;

import com.eicon.Infuse.comum.Entidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cliente")
public class Cliente extends Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nome_Cliente", length = 255)
    private String nomeCliente;

}
