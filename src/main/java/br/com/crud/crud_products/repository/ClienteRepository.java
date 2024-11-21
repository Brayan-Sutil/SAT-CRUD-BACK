package br.com.crud.crud_products.repository;

import br.com.crud.crud_products.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }