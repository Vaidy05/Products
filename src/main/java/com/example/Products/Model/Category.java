package com.example.Products.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int category_id;

    @Column(unique = true)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

    public Category() {
    }

    public Category(String category_name) {
        this.categoryName = category_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return categoryName;
    }

    public void setCategory_name(String category_name) {
        this.categoryName = category_name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
