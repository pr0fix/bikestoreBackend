package hh.sof003.bikestore.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof003.bikestore.domain.CategoryRepository;
import hh.sof003.bikestore.domain.Product;
import hh.sof003.bikestore.domain.ProductRepository;
import hh.sof003.bikestore.services.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    // list products
    @RequestMapping(value = "/productlist", method = RequestMethod.GET)
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "productlist";
    }

    // Add new product
    @RequestMapping(value = "/addproduct")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addproduct";
    }

    // save product
    @RequestMapping(value = "/saveproduct", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "redirect:productlist";
    }

    // delete product
    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProduct(@PathVariable("productId") Long productId, Model model) {
        productService.deleteProductById(productId);
        return "redirect:../productlist";
    }

    // edit product
    @RequestMapping(value = "/edit/{productId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editProduct(@PathVariable("productId") Long productId, Model model) {
        model.addAttribute(("product"), productRepository.findById(productId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editproduct";
    }
}
