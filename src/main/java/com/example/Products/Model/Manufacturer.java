package com.example.Products.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int manufacturer_id;

    @Column(unique = true)
    private String manufacturerName;

    private String manufacturer_details;

    @OneToMany(mappedBy = "manufacturer")
    private List<Product> productList;

    public Manufacturer() {
    }

    public Manufacturer(String manufacturer_name, String manufacturer_details) {
        this.manufacturerName = manufacturer_name;
        this.manufacturer_details = manufacturer_details;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getManufacturer_name() {
        return manufacturerName;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturerName = manufacturer_name;
    }

    public String getManufacturer_details() {
        return manufacturer_details;
    }

    public void setManufacturer_details(String manufacturer_details) {
        this.manufacturer_details = manufacturer_details;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
