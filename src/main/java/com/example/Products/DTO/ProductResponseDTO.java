package com.example.Products.DTO;

public class ProductResponseDTO {

    private String product_name;

    private String product_details;

    private int quantity;

    private int price_per_unit;

    private String product_status;

    private String category_name;

    private String manufacturer_name;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(String product_name, String product_details, int quantity, int price_per_unit, String product_status, String category_name, String manufacturer_name) {
        this.product_name = product_name;
        this.product_details = product_details;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.product_status = product_status;
        this.category_name = category_name;
        this.manufacturer_name = manufacturer_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getManufacturer_name() {
        return manufacturer_name;
    }

    public void setManufacturer_name(String manufacturer_name) {
        this.manufacturer_name = manufacturer_name;
    }
}
