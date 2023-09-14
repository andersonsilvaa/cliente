package com.devsuperior.cliente.repositories;

import com.devsuperior.cliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
