package com.eicon.Infuse.cliente.repository;

import com.eicon.Infuse.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
