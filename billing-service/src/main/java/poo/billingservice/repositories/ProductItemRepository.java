package poo.billingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poo.billingservice.entities.ProductItem;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
