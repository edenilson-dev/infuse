package com.eicon.Infuse.produto.entity.dto;

import com.eicon.Infuse.produto.entity.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement
public class ProdutoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface ProdutoView {
        public static interface ProdutoPost {}
        public static interface ProdutoPut {}
    }

    @JsonView(ProdutoView.ProdutoPost.class)
    private Long id;

    @JsonView({ProdutoView.ProdutoPost.class, ProdutoView.ProdutoPut.class})
    private String nomeProduto;

    public static ProdutoDto to(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .nomeProduto(produto.getNomeProduto())
                .build();
    }

}
