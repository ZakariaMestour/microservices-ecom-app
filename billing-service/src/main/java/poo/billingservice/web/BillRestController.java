package poo.billingservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import poo.billingservice.entities.Bill;
import poo.billingservice.entities.ProductItem;
import poo.billingservice.feign.CustomerRestClient;
import poo.billingservice.feign.ProductRestClient;
import poo.billingservice.repositories.BillRespository;
import poo.billingservice.repositories.ProductItemRepository;

@RestController
public class BillRestController {
    @Autowired
    private BillRespository billRespository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;
    @GetMapping("/bills/{id}")
    public Bill getBill( @PathVariable Long id) {
        Bill bill = billRespository.findById(id).get();
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(productItem -> {
           productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
        });
        return bill;
    }

}
