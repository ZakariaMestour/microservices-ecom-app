package poo.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import poo.billingservice.entities.Bill;
import poo.billingservice.entities.ProductItem;
import poo.billingservice.feign.CustomerRestClient;
import poo.billingservice.feign.ProductRestClient;
import poo.billingservice.model.Customer;
import poo.billingservice.model.Product;
import poo.billingservice.repositories.BillRespository;
import poo.billingservice.repositories.ProductItemRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRespository billRespository, ProductItemRepository productItemRepository
											, CustomerRestClient customerRestClient
											, ProductRestClient productRestClient) {
		return args -> {
			Collection<Customer> customers= customerRestClient.getAllCustomers().getContent();
			Collection<Product> products = productRestClient.getAllProducts().getContent();
			customers.forEach(customer -> {
				Bill bill = Bill.builder()
						.billingDate(new Date())
						.customerId(customer.getId())
						.build();
				billRespository.save(bill);
				products.forEach(product -> {
					ProductItem productItem=ProductItem.builder()
							.bill(bill)
							.productId(product.getId())
							.quantity(1+new Random().nextInt(10))
							.unitPrice(product.getPrice())
							.build();
					productItemRepository.save(productItem);
				});
			});
		};
	}
}
