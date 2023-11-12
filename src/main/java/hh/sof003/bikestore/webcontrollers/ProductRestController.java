package hh.sof003.bikestore.webcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.sof003.bikestore.domain.Category;
import hh.sof003.bikestore.domain.CategoryRepository;
import hh.sof003.bikestore.domain.Product;
import hh.sof003.bikestore.domain.ProductRepository;

@RestController
public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // REST service to list all products in productlist
    @GetMapping("/products")
    public @ResponseBody List<Product> listAllProductsREST() {
        return (List<Product>) productRepository.findAll();
    }

    // REST service to find a product by id
    @GetMapping("products/{productId}")
    public @ResponseBody Optional<Product> findProductByIdREST(@PathVariable("productId") Long productId) {
        return productRepository.findById(productId);
    }

    // REST service to list all categories in categorylist
    @GetMapping("/categories")
    public @ResponseBody List<Category> listAllCategoriesREST() {
        return (List<Category>) categoryRepository.findAll();
    }

    // REST service to find a category by id
    @GetMapping("/categories/{categoryId}")
    public @ResponseBody Optional<Category> findCategoryByIdREST(@PathVariable("categoryId") Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

}
