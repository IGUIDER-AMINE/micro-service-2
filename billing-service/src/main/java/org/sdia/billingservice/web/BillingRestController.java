package org.sdia.billingservice.web;

import org.sdia.billingservice.entities.Bill;
import org.sdia.billingservice.entities.ProductItem;
import org.sdia.billingservice.feign.CustomerRestClient;
import org.sdia.billingservice.feign.ProductItemRestClient;
import org.sdia.billingservice.model.Customer;
import org.sdia.billingservice.model.Product;
import org.sdia.billingservice.repositories.BillRepository;
import org.sdia.billingservice.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }


    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi->{
            Product product = productItemRestClient.getProductById(pi.getProductId());
            //pi.setProduct(product);
            pi.setProductName(product.getName());
        });
        return  bill;
    }
}
