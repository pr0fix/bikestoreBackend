package hh.sof003.bikestore;

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
// import hh.sof003.bikestore.domain.Order;
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
			productRepository.save(
					new Product("Kawasaki", "ZX-6R", 5990.00, "Kawasaki ZX-6R", "A very fast sportsbike", "Matte grey",
							2008, category1));
			productRepository.save(
					new Product("BMW", "S1000RR", 15990.00, "BMW S1000RR", "A trackbike made for the streets",
							"White", 2014, category1));

			// accounts
			log.info("Creating and saving a couple of accounts");
			accountRepository.save(
					new Account("user", "$2a$10$mgya6fkqSRPe1sJNsxunl.5.20Q4SIG7iq4c/fLBw4Gc.bAJooS/e", "User",
							"Userinen", "user@testuser.com", "0501234567", "USER"));

			accountRepository.save(
					new Account("admin", "$2a$10$nGhhQkIi6Nofxx5Sadw9GOE/v2LiqWxTSu1X2MMMKRwEQwDi9j49e", "Admin",
							"Adminen", "admin@testadmin.com", "0507654321", "ADMIN"));

			// fetch products
			log.info("fetch all products");
			for (Product product : productRepository.findAll()) {
				log.info(product.toString());
			}
		};
	};
}
