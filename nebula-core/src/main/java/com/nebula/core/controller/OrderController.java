package com.nebula.core.controller;

import com.nebula.core.dto.OrderRequest;
import com.nebula.core.model.Order;
import com.nebula.core.model.Product;
import com.nebula.core.repository.OrderRepository;
import com.nebula.core.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping // Verbo POST = Criar nova informação
    public Order criarPedido(@RequestBody OrderRequest request) {
        // 1. Buscar o produto pelo ID que veio do React
        Product produto = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        // 2. Montar o Pedido (Recibo)
        Order novoPedido = new Order();
        novoPedido.setCustomerName(request.getCustomerName());
        novoPedido.setCustomerEmail(request.getCustomerEmail());
        novoPedido.setProduct(produto); // Vincula o produto ao pedido
        novoPedido.setOrderDate(LocalDateTime.now()); // Carimba a hora atual

        // 3. Salvar no Banco
        return orderRepository.save(novoPedido);
    }
    @PutMapping("/{id}/cancel")
    public Order cancelarPedido(@PathVariable Long id) {
        // 1. Achar o pedido
        Order pedido = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        // 2. Mudar o estado (Aqui entraria a lógica do ProvisioningService pra cortar a rede!)
        pedido.setStatus("CANCELED");

        // 3. Salvar a alteração
        return orderRepository.save(pedido);
    }

    // Aproveitando, vamos criar um GET para o Admin ver os pedidos
    @GetMapping
    public java.util.List<Order> listarPedidos() {
        return orderRepository.findAll();
    }

}