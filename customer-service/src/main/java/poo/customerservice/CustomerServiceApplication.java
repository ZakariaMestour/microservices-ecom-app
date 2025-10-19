package poo.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import poo.customerservice.entities.Customer;
import poo.customerservice.repositories.CustomerRepository;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner( CustomerRepository customersRepository) {
        return args -> {
            customersRepository.save(
                    Customer.builder()
                            .name("zakaria")
                            .email("zakaria@gmail.com")
                            .build()
            );
            customersRepository.save(
                    Customer.builder()
                            .name("ahmed")
                            .email("ahmed@gmail.com")
                            .build()
            );
            customersRepository.save(
                    Customer.builder()
                            .name("reda")
                            .email("reda@gmail.com")
                            .build()
            );
        };

    }
}
