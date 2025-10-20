package poo.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import poo.inventoryservice.entities.Product;
import poo.inventoryservice.repositories.ProductRepository;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("computer")
                            .price(10000.0)
                            .quantity(10)

                            .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("printer")
                    .price(1999.0)
                    .quantity(150)

                    .build());
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("screen")
                    .price(9994.0)
                    .quantity(50)

                    .build());
        };

    }
}
