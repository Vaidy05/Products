package com.example.Products.Model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String productName;

    private int no_of_products;


    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Mobile> mobileList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Fashion> fashionList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Shoes> shoesList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Chair> chairList;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Pen> penList;

    public Product() {
    }

    public Product(String productName) {
        this.productName = productName;
        this.no_of_products = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Mobile> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<Mobile> mobileList) {
        this.mobileList = mobileList;
    }

    public List<Fashion> getFashionList() {
        return fashionList;
    }

    public void setFashionList(List<Fashion> fashionList) {
        this.fashionList = fashionList;
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public void setShoesList(List<Shoes> shoesList) {
        this.shoesList = shoesList;
    }

    public List<Chair> getChairList() {
        return chairList;
    }

    public void setChairList(List<Chair> chairList) {
        this.chairList = chairList;
    }

    public List<Pen> getPenList() {
        return penList;
    }

    public void setPenList(List<Pen> penList) {
        this.penList = penList;
    }
}
