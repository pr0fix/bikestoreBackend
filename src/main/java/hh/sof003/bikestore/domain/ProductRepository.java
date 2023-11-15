package hh.sof003.bikestore.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findByName(String name);   
}
