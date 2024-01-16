package com.eicon.Infuse.produto.entity;

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
@Table(name = "tb_produto")
public class Produto extends Entidade implements Serializable {

    @Column(name = "nome_Produto", length = 255)
    private String nomeProduto;

}
