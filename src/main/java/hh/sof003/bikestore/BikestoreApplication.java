package hh.sof003.bikestore;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof003.bikestore.domain.Account;
import hh.sof003.bikestore.domain.AccountRepository;
import hh.sof003.bikestore.domain.Category;
import hh.sof003.bikestore.domain.CategoryRepository;
import hh.sof003.bikestore.domain.Order;
import hh.sof003.bikestore.domain.OrderRepository;
import hh.sof003.bikestore.domain.Product;
import hh.sof003.bikestore.domain.ProductRepository;

@SpringBootApplication
public class BikestoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BikestoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BikestoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner ProductRunner(ProductRepository productRepository, AccountRepository accountRepository,
			OrderRepository orderRepository, CategoryRepository categoryRepository) {
		return (args) -> {

			// categories
			log.info("Saving a couple of categories");
			Category category1 = new Category("Motorcycles");
			categoryRepository.save(category1);
			Category category2 = new Category("Gear");
			categoryRepository.save(category2);
			Category category3 = new Category("Spareparts");
			categoryRepository.save(category3);

			// products
			log.info("Saving a couple of products into database");
			Product product1 = productRepository.save(
					new Product("Kawasaki", "ZX-6R", 5990.00, "Kawasaki ZX-6R", "A very fast sportsbike", "Matte grey",
							2008, category1));
			Product product2 = productRepository.save(
					new Product("BMW", "S1000RR", 15990.00, "BMW S1000RR", "A trackbike made for the streets",
							"White", 2014, category1));

			Product product3 = productRepository.save(
					new Product("Yamaha", "YZF-R1", 3990.00, "Yamaha R1", "Old but gold",
							"Blue with white accents", 2001, category1));

			// accounts
			log.info("Creating and saving a couple of accounts");
			Account user = accountRepository.save(
					new Account("user", "$2a$10$mgya6fkqSRPe1sJNsxunl.5.20Q4SIG7iq4c/fLBw4Gc.bAJooS/e", "User",
							"Userinen", "user@testuser.com", "0501234567", "USER"));

			Account admin = accountRepository.save(
					new Account("admin", "$2a$10$nGhhQkIi6Nofxx5Sadw9GOE/v2LiqWxTSu1X2MMMKRwEQwDi9j49e", "Admin",
							"Adminen", "admin@testadmin.com", "0507654321", "ADMIN"));

			// first order items
			List<Product> orderOneItems = new ArrayList<>();
			orderOneItems.add(product1);
			orderOneItems.add(product2);
			orderOneItems.add(product3);

			// second order items
			List<Product> orderTwoItems = new ArrayList<>();
			orderTwoItems.add(product3);
			orderTwoItems.add(product2);

			// orders
			// Order order1 = orderRepository.save(new Order(orderOneItems, admin, "25-10-2023", "2-11-2023", "debit"));
			// Order order2 = orderRepository.save(new Order(orderTwoItems, user, "1-1-2023", "5-9-2023", "cash"));

			// fetch categories
			log.info("fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}

			// fetch products
			log.info("fetch all products");
			for (Product product : productRepository.findAll()) {
				log.info(product.toString());
			}

			// fetch accounts
			log.info("fetch all accounts");
			for (Account account : accountRepository.findAll()) {
				log.info(account.toString());
			}

			// fetch orders
			// log.info("fetch all orders");
			// for (Order order : orderRepository.findAll()) {
			// 	log.info(order.toString());
			// }
		};
	};
}
