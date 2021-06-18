package com.example.appcupom;

import java.util.Date;

public class Product {
    private int id;
    private String productName;
    private Double price;
    private Integer quantity;

    public Product(){}

    public Product(String productName, Double price, Integer quantity){
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + " - " + productName + " | " + price + " | " + quantity;
    }
}
