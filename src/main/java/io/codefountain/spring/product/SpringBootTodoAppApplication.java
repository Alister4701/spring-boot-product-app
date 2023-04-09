package io.codefountain.spring.product;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.codefountain.spring.product.domain.Product;
import io.codefountain.spring.product.repository.ProductRepository;

@EnableAutoConfiguration(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@SpringBootApplication
public class SpringBootTodoAppApplication implements CommandLineRunner {

	@Autowired
	public ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootTodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * Collection<Todo> todos = Arrays.asList(new Todo("Learn Spring", "Yes"), new
		 * Todo("Learn Driving", "No"), new Todo("Go for a Walk", "No"), new
		 * Todo("Cook Dinner", "Yes")); todos.forEach(todoRepository::save);
		 */
		Collection<Product> products = Arrays.asList(new Product("Cable", 3, 189.65), new Product("Pipes", 4, 178.10), new Product("Raw materials", 2, 120.50), new Product("Wood", 6, 17.70));
		products.forEach(productRepository::save);
		
	}
}
