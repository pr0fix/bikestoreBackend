package hh.sof003.bikestore.domain;

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

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "order_products",
        joinColumns = @JoinColumn(name="orderId"),
        inverseJoinColumns = @JoinColumn(name="productId")
        )
    private List<Product> items = new ArrayList<>();

    @ManyToOne
    @JsonIgnoreProperties("orders")
    @JoinColumn(name = "accountId")
    private Account account;

    private String orderDate;
    private String deliveryDate;
    private String paymentMethod;

    public Order(List<Product> items, Account account, String orderDate, String deliveryDate, String paymentMethod) {
        this.items = items;
        this.account = account;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
    }

    public Order() {
        this.items = null;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Order [id=" + orderId + "account=" + account + ", orderDate=" + orderDate
                + ", deliveryDate=" + deliveryDate + ", paymentMethod=" + paymentMethod + "]";
    }

}
