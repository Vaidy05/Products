package com.example.Products.Model;

import jakarta.persistence.*;


@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;

    private String productName;

    private String product_details;

    private int quantity;

    private int price_per_unit;

    private String product_status;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn
    private Manufacturer manufacturer;

    public Product() {
    }

    public Product(String product_name, String product_details, int quantity, int price_per_unit, String product_status) {
        this.productName = product_name;
        this.product_details = product_details;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.product_status = product_status;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return productName;
    }

    public void setProduct_name(String product_name) {
        this.productName = product_name;
    }

    public String getProduct_details() {
        return product_details;
    }

    public void setProduct_details(String product_details) {
        this.product_details = product_details;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
