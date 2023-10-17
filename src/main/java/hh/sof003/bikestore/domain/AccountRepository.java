package hh.sof003.bikestore.domain;

import org.springframework.data.repository.CrudRepository;


public interface AccountRepository extends CrudRepository<Account, Long>{
    Account findByUsername(String username);
}
