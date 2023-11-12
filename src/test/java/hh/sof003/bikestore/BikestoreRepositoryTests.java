package hh.sof003.bikestore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof003.bikestore.domain.Account;
import hh.sof003.bikestore.domain.AccountRepository;
import hh.sof003.bikestore.domain.CategoryRepository;
import hh.sof003.bikestore.domain.OrderRepository;
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

    // TODO: CategoryRepository tests:

    // TODO: OrderRepository tests:

    // TODO: ProductRepository tests:

}
