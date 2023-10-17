package hh.sof003.bikestore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String brand;
    private String model;
    private Double price;
    private String name;
    private String description;
    private String color;
    private int manifacturingYear;
    // possibly add kilometers driven?

    @ManyToOne
    @JsonIgnoreProperties("products")
    @JoinColumn(name = "categoryId")
    private Category category;

    public Product() {
    }

    public Product(String brand, String model, Double price, String name, String description, String color,
            int manifacturingYear, Category category) {
        super();
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.name = name;
        this.description = description;
        this.color = color;
        this.manifacturingYear = manifacturingYear;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getManifacturingYear() {
        return manifacturingYear;
    }

    public void setManifacturingYear(int manifacturingYear) {
        this.manifacturingYear = manifacturingYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
