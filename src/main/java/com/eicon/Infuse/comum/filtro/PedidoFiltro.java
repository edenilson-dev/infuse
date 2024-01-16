package com.eicon.Infuse.comum.filtro;

import com.eicon.Infuse.pedido.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFiltro {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdFim;

    public Specification<Pedido> toSpec(){
        return (root, query, builder) -> {
            List<Predicate> predicados = new ArrayList<>();

            if (id != null) {
                Path<Long> mapid = root.<Long>get("id");
                Predicate predicadoId = builder.equal(mapid, id);
                predicados.add(predicadoId);
            }

            if (createdInicio != null) {
                Path<LocalDate> mapcreatedAt = root.<LocalDate>get("createdAt");
                Predicate predicadoCreatedInicio = builder.greaterThanOrEqualTo(mapcreatedAt, createdInicio);
                predicados.add(predicadoCreatedInicio);
            }

            if (createdFim != null) {
                Path<LocalDate> mapupdatedAt = root.<LocalDate>get("createdAt");
                Predicate predicadoUpdatedFim = builder.lessThanOrEqualTo(mapupdatedAt, createdFim);
                predicados.add(predicadoUpdatedFim);
            }

            return builder.and(predicados.toArray(new Predicate[0]));
        };
    }


}
