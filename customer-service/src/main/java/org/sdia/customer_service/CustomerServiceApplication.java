package org.sdia.customer_service;

import org.sdia.customer_service.entities.Customer;
import org.sdia.customer_service.respostories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CustomerRepository customerRepository , RepositoryRestConfiguration repoRestConfiguration) {
		repoRestConfiguration.exposeIdsFor(Customer.class);
		//repoRestConfiguration.exposeIdsFor(Customer.class) =>  http://localhost:8888/CUSTOMER-SERVICE/customers/1
		return args -> {
			customerRepository.save(new Customer(null,"AMINE", "AMINE@Gmail.com"));
			customerRepository.save(new Customer(null,"IGUIDER", "IGUIDER@Gmail.com"));
			customerRepository.save(new Customer(null,"AHMED", "AHMED@Gmail.com"));
			customerRepository.findAll().forEach(
					customer -> System.out.println(customer.toString())
			);
		};
	}
}
