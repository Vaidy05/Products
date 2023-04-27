package com.example.Products.DTO;

public class ProductRequestDTO {

    private String product_name;

    private String product_details;

    private int quantity;

    private int price_per_unit;

    private String product_status;

    private int category_id;

    private int manufacturer_id;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String product_name, String product_details, int quantity, int price_per_unit, String product_status, int category_id, int manufacturer_id) {
        this.product_name = product_name;
        this.product_details = product_details;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.product_status = product_status;
        this.category_id = category_id;
        this.manufacturer_id = manufacturer_id;
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }
}
