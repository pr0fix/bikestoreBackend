package hh.sof003.bikestore.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_products", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> items = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("orders")
    @JoinColumn(name = "accountId")
    private Account account;

    @NotNull
    private LocalDate orderDate;
    
    @NotNull
    private LocalDate deliveryDate;

    @NotBlank
    private String paymentMethod;

    public Order(List<Product> items, Account account, LocalDate orderDate, LocalDate deliveryDate,
            String paymentMethod) {
        this.items = items;
        this.account = account;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
    }

    public Order() {
        this.items = new ArrayList<>();
        this.account = null;
        this.orderDate = null;
        this.deliveryDate = null;
        this.paymentMethod = null;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order [id=" + orderId + ", account=" + account + ", orderDate=" + orderDate
                + ", deliveryDate=" + deliveryDate + ", paymentMethod=" + paymentMethod + "]";
    }

}
