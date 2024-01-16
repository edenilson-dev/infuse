package com.eicon.Infuse.comum;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Entidade implements Serializable {

    private static final long serialVersionUID = 3270601256011818010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column()
    private LocalDate updatedAt;

    public Entidade() {
    }

    public Entidade(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (getId() == null ? super.hashCode() : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && (obj.getClass().equals(getClass())) && obj.hashCode() == hashCode();
    }
}
