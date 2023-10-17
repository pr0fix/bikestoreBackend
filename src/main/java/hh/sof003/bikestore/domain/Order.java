package hh.sof003.bikestore.domain;

import java.time.LocalDateTime;
// import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    //private List<Product> items;

    private Long accountId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String paymentMethod;

    public Order(//List<Product> items,
     Long accountId, LocalDateTime orderDate, LocalDateTime deliveryDate,
            String paymentMethod) {
       // this.items = items;
        this.accountId = accountId;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // public List<Product> getItems() {
    //     return items;
    // }

    // public void setItems(List<Product> items) {
    //     this.items = items;
    // }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
