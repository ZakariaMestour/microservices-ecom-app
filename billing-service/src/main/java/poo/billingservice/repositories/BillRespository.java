package poo.billingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import poo.billingservice.entities.Bill;

public interface BillRespository extends JpaRepository<Bill,Long> {
}
