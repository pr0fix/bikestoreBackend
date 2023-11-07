package hh.sof003.bikestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.sof003.bikestore.domain.Order;
import hh.sof003.bikestore.domain.OrderRepository;
import hh.sof003.bikestore.domain.Product;
import hh.sof003.bikestore.domain.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void deleteProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(product != null) {
            for (Order order : product.getOrders()) {
                order.getItems().remove(product);
                orderRepository.save(order);
            }
            productRepository.delete(product);
        }
    }
}
