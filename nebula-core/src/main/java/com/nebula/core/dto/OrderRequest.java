package com.nebula.core.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private String customerName;
    private String customerEmail;
    private Long productId; // O React vai mandar só o ID (Ex: 1)
}