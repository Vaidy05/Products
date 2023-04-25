package com.example.Products.DTO;


import java.util.Date;

public class PenDTO {

    private String brand;

    private int quantity;

    private String date;

    private int price_per_unit;

    private String color;

    private String ink;

    private String type;

    public PenDTO() {
    }

    public PenDTO(String brand, int quantity, String date, int price_per_unit, String color, String ink, String type) {
        this.brand = brand;
        this.quantity = quantity;
        this.date = date;
        this.price_per_unit = price_per_unit;
        this.color = color;
        this.ink = ink;
        this.type = type;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInk() {
        return ink;
    }

    public void setInk(String ink) {
        this.ink = ink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
