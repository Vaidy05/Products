package com.example.Products.DTO;

public class ProductDTO {

    private String productName;

    private int no_of_products;

    public ProductDTO() {
    }

    public ProductDTO(String productName, int no_of_products) {
        this.productName = productName;
        this.no_of_products = no_of_products;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNo_of_products() {
        return no_of_products;
    }

    public void setNo_of_products(int no_of_products) {
        this.no_of_products = no_of_products;
    }
}
