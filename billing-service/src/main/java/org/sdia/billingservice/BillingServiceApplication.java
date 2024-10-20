package org.sdia.billingservice;

import org.sdia.billingservice.entities.Bill;
import org.sdia.billingservice.entities.ProductItem;
import org.sdia.billingservice.feign.CustomerRestClient;
import org.sdia.billingservice.feign.ProductItemRestClient;
import org.sdia.billingservice.model.Customer;
import org.sdia.billingservice.model.Product;
import org.sdia.billingservice.repositories.BillRepository;
import org.sdia.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            ProductItemRepository productItemRepository,
            CustomerRestClient CustomerRestClient,
            ProductItemRestClient productItemRestClient
    ){ return args -> {
        Customer customer =CustomerRestClient.getCustomerById(1L);
        Bill bill1 = billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
        PagedModel<Product> productPagedModel = productItemRestClient.pageProducts(0,3);
        productPagedModel.forEach(p->{
            ProductItem productItem = new ProductItem();
            productItem.setPrice(p.getPrice());
            productItem.setQuantity(1+new Random().nextInt(100));
            productItem.setBill(bill1);
            productItem.setProductId(p.getId());
            productItemRepository.save(productItem);
        });
        /*System.out.println("-----------------------------");
        System.out.println(customer.getId());
        System.out.println(customer.getName());
        System.out.println(customer.getEmail());*/

    };
    }
}
