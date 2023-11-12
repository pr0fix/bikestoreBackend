package hh.sof003.bikestore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);

    Account findByEmail(String email);

    List<Account> findUserByUsername(String username);
}
