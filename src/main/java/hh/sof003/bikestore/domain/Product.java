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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank(message = "Please give product brand.")
    private String brand;

    @NotBlank(message = "Please give product model.")
    private String model;

    @NotNull(message = "Please give product price.")
    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to \"0.0\".")
    private Double price;

    @NotBlank(message = "Please give product name.")
    private String name;

    @NotBlank(message = "Please give product description.")
    private String description;

    @NotBlank(message = "Please give product color.")
    private String color;

    @NotNull(message = "Please give product manufacturing year.")
    @Min(value = 1900, message = "Manufacturing year must be after 1900.")
    private int manufacturingYear;

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

    public void setManufacturingYear(int manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
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
