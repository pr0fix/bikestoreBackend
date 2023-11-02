package hh.sof003.bikestore.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.sof003.bikestore.domain.Product;
import hh.sof003.bikestore.domain.ProductRepository;

@RestController
public class ProductRestController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/products")
    public @ResponseBody List<Product> listAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

}
