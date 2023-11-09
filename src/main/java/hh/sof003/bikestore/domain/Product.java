package hh.sof003.bikestore.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String brand;
    private String model;
    private Double price;
    private String name;
    private String description;
    private String color;
    private int manufacturingYear;
    // possibly add kilometers driven?

    @ManyToOne
    @JsonIgnoreProperties("products")
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

    public Product() {
        this.brand = null;
        this.model = null;
        this.price = 0.0;
        this.name = null;
        this.description = null;
        this.color = null;
        this.manufacturingYear = 0;
        this.category = null;
    }

    public Product(String brand, String model, Double price, String name, String description, String color,
            int manufacturingYear, Category category) {
        super();
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.name = name;
        this.description = description;
        this.color = color;
        this.manufacturingYear = manufacturingYear;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(int manifacturingYear) {
        this.manufacturingYear = manifacturingYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product [id=" + productId + ", brand=" + brand + ", model=" + model + ", price=" + price + ", name="
                + name
                + ", description=" + description + ", color=" + color + ", manifacturingYear=" + manufacturingYear
                + ", category=" + category;
    }

}
