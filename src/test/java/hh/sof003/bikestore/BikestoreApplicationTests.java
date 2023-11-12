package hh.sof003.bikestore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import hh.sof003.bikestore.webcontrollers.AccountController;
import hh.sof003.bikestore.webcontrollers.CategoryController;
import hh.sof003.bikestore.webcontrollers.OrderController;
import hh.sof003.bikestore.webcontrollers.ProductController;
import hh.sof003.bikestore.webcontrollers.ProductRestController;

@SpringBootTest
class BikestoreApplicationTests {
	@Autowired
	private AccountController accountController;

	@Autowired
	private CategoryController categoryController;

	@Autowired
	private OrderController orderController;

	@Autowired
	private ProductController productController;

	@Autowired
	private ProductRestController productRestController;

	@Test
	void contextLoads() throws Exception {
		assertThat(accountController).isNotNull();
		assertThat(categoryController).isNotNull();
		assertThat(orderController).isNotNull();
		assertThat(productController).isNotNull();
		assertThat(productRestController).isNotNull();

	}

}
