package com.nebula.core.controller; // Ajuste se você não criou a pasta controller

import com.nebula.core.model.Product;
import com.nebula.core.repository.ProductRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products") // O endereço da loja
@CrossOrigin(origins = "*") // Libera pro React acessar
public class ProductController {

    private final ProductRepository productRepository;

    // O Spring injeta o "Gerente de Estoque" aqui automaticamente
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> listarTodos() {
        // O método findAll() faz o "SELECT * FROM tb_product" sozinho!
        return productRepository.findAll();
    }
}