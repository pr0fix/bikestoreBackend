package hh.sof003.bikestore.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findByAccount(Account account);
}
