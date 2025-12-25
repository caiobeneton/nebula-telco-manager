package com.nebula.core.controller; // Ajuste se você não criou a pasta controller

import com.nebula.core.model.Product;
import com.nebula.core.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

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
        // O findAll() faz o "SELECT * FROM tb_product" sozinho!
        return productRepository.findAll();
    }

    @PostMapping // Agora aceitamos POST também em /api/products
    public Product cadastrarProduto(@RequestBody Product produto) {
        // O Java recebe o JSON, transforma em Objeto Product e salva
        return productRepository.save(produto);
    }

    @PatchMapping("/{id}/price")
    public Product editarProduto(@PathVariable Long id, @RequestBody java.math.BigDecimal novoPreco) {
        Product produto = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        produto.setPrice(novoPreco);

        return  productRepository.save(produto);
    }
}