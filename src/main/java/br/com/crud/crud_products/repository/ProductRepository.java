package br.com.crud.crud_products.repository;

import br.com.crud.crud_products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }