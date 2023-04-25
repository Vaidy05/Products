package com.example.Products.DTO;


import java.util.Date;

public class FashionDTO {

    private String brand;

    private int quantity;

    private String date;

    private int price_per_unit;

    private String material;

    private String color;

    private String type;

    private String size;

    public FashionDTO() {
    }

    public FashionDTO(String brand, int quantity, String date, int price_per_unit, String material, String color, String type, String size) {
        this.brand = brand;
        this.quantity = quantity;
        this.date = date;
        this.price_per_unit = price_per_unit;
        this.material = material;
        this.color = color;
        this.type = type;
        this.size = size;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
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
}
