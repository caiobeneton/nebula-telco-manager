package com.nebula.core.repository;

import com.nebula.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// <Product, Long> = <Tipo da Tabela, Tipo do ID>
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Só de fazer isso, você ganhou: .save(), .findAll(), .delete(), .findById()...
    // É mágica pura.
}