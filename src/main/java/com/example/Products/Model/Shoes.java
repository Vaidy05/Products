package com.example.Products.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;

    private int quantity;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    private int price_per_unit;

    private String material;

    private String color;

    private String type;

    private String size;

    private String heelType;

    @ManyToOne
    @JoinColumn
    private Product product;

    public Shoes() {
    }



    public Shoes(String brand, int quantity, Date date, int price_per_unit, String material, String color, String type, String size, String heelType) {
        this.brand = brand;
        this.quantity = quantity;
        this.date = date;
        this.price_per_unit = price_per_unit;
        this.material = material;
        this.color = color;
        this.type = type;
        this.size = size;
        this.heelType = heelType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHeelType() {
        return heelType;
    }

    public void setHeelType(String heelType) {
        this.heelType = heelType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }
}
