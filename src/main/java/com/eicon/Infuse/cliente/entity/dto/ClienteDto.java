package com.eicon.Infuse.cliente.entity.dto;

import com.eicon.Infuse.cliente.entity.Cliente;
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
public class ClienteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface ClienteView {
        public static interface ClientePost {}
        public static interface ClientePut {}
    }

    @JsonView(ClienteView.ClientePost.class)
    private Long id;

    @JsonView({ClienteView.ClientePost.class, ClienteView.ClientePut.class})
    private String nomeCliente;

    public static ClienteDto to(Cliente cliente) {
        return ClienteDto.builder()
                .id(cliente.getId())
                .nomeCliente(cliente.getNomeCliente())
                .build();
    }

}
