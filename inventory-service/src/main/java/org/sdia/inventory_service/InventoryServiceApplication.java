package org.sdia.inventory_service;

import org.sdia.inventory_service.entities.Product;
import org.sdia.inventory_service.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null,"Ordinateur", 788,12));
			productRepository.save(new Product(null,"Imprimente", 88,129));
			productRepository.save(new Product(null,"Smartphone", 1288,112));
			productRepository.findAll().forEach(p-> System.out.println(p.toString()));
		};
	}
}
