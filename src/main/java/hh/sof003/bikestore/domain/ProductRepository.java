package hh.sof003.bikestore.domain;

import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long>{
    Product findByName(String name);   
}
