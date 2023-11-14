package hh.sof003.bikestore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof003.bikestore.domain.Account;
import hh.sof003.bikestore.domain.AccountRepository;
import hh.sof003.bikestore.domain.Category;
import hh.sof003.bikestore.domain.CategoryRepository;
import hh.sof003.bikestore.domain.Order;
import hh.sof003.bikestore.domain.OrderRepository;
import hh.sof003.bikestore.domain.Product;
import hh.sof003.bikestore.domain.ProductRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BikestoreRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // AccountRepository tests:

    // Create account
    @Test
    public void createNewAccount() {
        Account account = new Account("testUser", "$2a$12$yPQtwI.//kN17rUhUTgr6eUoD8bRyOidYOqbV5A96NG.SDkIpLczi",
                "testuser", "testuser", "testuser@test.com", "0401234567", "USER");
        accountRepository.save(account);
        assertThat(account.getAccountId()).isNotNull();
    }

    // Delete account
    @Test
    public void deleteAccount() {
        Account account = new Account("testUser", "$2a$12$yPQtwI.//kN17rUhUTgr6eUoD8bRyOidYOqbV5A96NG.SDkIpLczi",
                "testuser", "testuser", "testuser@test.com", "0401234567", "USER");
        accountRepository.save(account);
        Long accountId = account.getAccountId();
        accountRepository.deleteById(accountId);
        Account deletedAccount = accountRepository.findById(accountId).orElse(null);
        assertNull(deletedAccount);
    }

    // Find account by username
    @Test
    public void findAccountByUsername() {
        List<Account> accounts = accountRepository.findUserByUsername("user");
        assertThat(accounts).hasSize(1);
        assertThat(accounts.get(0).getFirstName()).isEqualTo("User");
    }

    // Find all accounts
    @Test
    public void findAllAccounts() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        assertThat(accounts).isNotNull();
        assertThat(accounts.iterator().hasNext());
    }

    // CategoryRepository tests:

    // Create and save a new category to database
    @Test
    public void createNewCategory() {
        Category category = new Category("Tires");
        categoryRepository.save(category);

        assertThat(category.getCategoryId()).isNotNull();
    }

    // Delete category from database
    @Test
    public void deleteCategory() {
        Category category = new Category("Tires");
        categoryRepository.save(category);
        Long categoryId = category.getCategoryId();
        categoryRepository.deleteById(categoryId);
        Category deletedCategory = categoryRepository.findById(categoryId).orElse(null);
        assertNull(deletedCategory);
    }

    // Find category by name from database
    @Test
    public void findCategoryByName() {
        List<Category> categories = categoryRepository.findByName("Motorcycles");

        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getCategoryId()).isEqualTo(1);
    }

    // Find all categories in database
    @Test
    public void findAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        assertThat(categories).isNotNull();
        assertThat(categories.iterator().hasNext());
    }

    // OrderRepository tests:

    // Create and save a new order to database
    @Test
    public void createNewOrder() {
        Product product1 = new Product();
        Product product2 = new Product();
        Account account = new Account("testuser", "$2a$12$KMLiEtDqGY6ZCQGvPlt/auO0cNF7O39QElnRFbcOwCnPNH9ief1re",
                "test", "user", "test@test.com", "12345678910", "USER");
        List<Product> items = new ArrayList<>();
        items.add(product1);
        items.add(product2);
        Order order = new Order(items, account, LocalDate.now(), LocalDate.of(2030, 2, 8), "debit");
        orderRepository.save(order);
        assertThat(order.getOrderId()).isNotNull();
    }

    // Delete order from database
    @Test
    public void deleteOrder() {
        Product product1 = new Product();
        Product product2 = new Product();
        Account account = new Account("testuser", "$2a$12$KMLiEtDqGY6ZCQGvPlt/auO0cNF7O39QElnRFbcOwCnPNH9ief1re",
                "test", "user", "test@test.com", "12345678910", "USER");
        List<Product> items = new ArrayList<>();
        items.add(product1);
        items.add(product2);
        Order order = new Order(items, account, LocalDate.now(), LocalDate.of(2030, 2, 8), "debit");
        orderRepository.save(order);

        Long orderId = order.getOrderId();
        orderRepository.deleteById(orderId);

        Order deletedOrder = orderRepository.findById(orderId).orElse(null);
        assertNull(deletedOrder);
    }

    // Find order from database by account username
    @Test
    public void findOrderByAccount() {
        List<Order> order = orderRepository.findByAccount(accountRepository.findByUsername("user"));
        List<Order> nonExistentOrder = (List<Order>) orderRepository
                .findByAccount(accountRepository.findByUsername("nonexistentuser"));

        assertThat(order).isNotNull();
        assertThat(nonExistentOrder.size()).isEqualTo(0);
    }

    // Find all orders from database
    @Test
    public void findAllOrders() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        assertThat(orders).isNotNull();
        assertThat(orders.iterator().hasNext());
        assertThat(orders).isNotEmpty();
    }

    // TODO: ProductRepository tests:
    @Test
    public void createNewProduct() {

    }

    @Test
    public void deleteProduct() {

    }

    @Test
    public void findProductByName() {

    }

    @Test
    public void findAllProducts() {
        
    }
}

