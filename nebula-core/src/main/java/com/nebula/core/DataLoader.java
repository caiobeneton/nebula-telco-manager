package com.nebula.core;

import com.nebula.core.model.Product;
import com.nebula.core.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component // Diz pro Spring: "Isso é uma peça do sistema, carregue ela."
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;

    // Injeção de Dependência: O Spring te dá o Repository pronto
    public DataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se já tem produtos pra não duplicar toda vez que reiniciar
        if (productRepository.count() == 0) {
            System.out.println("🤖 [DATALOADER] Iniciando carga de dados inicial...");

            Product p1 = new Product();
            p1.setName("Nebula Ilimitado 5G");
            p1.setDescription("Dados ilimitados, QoS Ouro, Sem Fidelidade");
            p1.setPrice(new BigDecimal("99.90"));
            p1.setTechnicalSpec("QOS_GOLD_SPEED_UNLIMITED");

            Product p2 = new Product();
            p2.setName("Nebula Controle 20GB");
            p2.setDescription("20GB de internet + WhatsApp Grátis");
            p2.setPrice(new BigDecimal("49.90"));
            p2.setTechnicalSpec("QOS_SILVER_SPEED_20MBPS");

            // Salva no banco
            productRepository.save(p1);
            productRepository.save(p2);

            System.out.println("✅ [DATALOADER] Produtos criados com sucesso!");
        } else {
            System.out.println("ℹ️ [DATALOADER] O banco já tem dados. Pulando carga.");
        }
    }
}