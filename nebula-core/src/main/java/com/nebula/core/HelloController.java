package com.nebula.core;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController // Diz pro Spring: "Eu sou um Recepcionista web"
@CrossOrigin(origins = "*") // Diz: "Aceito conexões de qualquer lugar (inclusive do React)"

public class HelloController {
    @GetMapping("/api/hello") // Diz: "Quando alguém acessar o endereço /api/hello..."
    public String dizerOla() {
        return "Olá, Mundo! O Nebula Core está ONLINE!"; // "...responda isso aqui."
    }
}
