package com.nebula.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;

@Data // O Lombok cria os Getters, Setters e toString() sozinho!
@Entity // Diz pro Spring: "Isso aqui é uma tabela no banco"
@Table(name = "tb_product") // "O nome da tabela será tb_product"
public class Product {

    @Id // "Essa é a Chave Primária (PK)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // "O banco gera o ID sozinho (Auto Increment)"
    private Long id;

    private String name; // Ex: "Plano Nebula Gold"

    private String description; // Ex: "5G Ilimitado + Spotify"

    private BigDecimal price; // Ex: 99.90 (Sempre use BigDecimal para dinheiro!)

    // Campo técnico pra simular rede
    private String technicalSpec; // Ex: "QOS_LEVEL_5_SPEED_100MBPS"
}