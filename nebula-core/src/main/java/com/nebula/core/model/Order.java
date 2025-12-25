package com.nebula.core.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName; // Nome do Cliente (Ex: Caio)

    private String customerEmail; // Email (Ex: caio@vivo.com)

    // O Pulo do Gato: Relacionamento entre Tabelas
    // Um pedido "tem um" produto vinculado.
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime orderDate; // Data e hora da compra

    // Novo campo! (Valores: "ACTIVE", "CANCELED")
    private String status = "ACTIVE";
}