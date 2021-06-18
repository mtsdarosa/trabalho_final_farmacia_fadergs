package com.example.appcupom;

public class Order {
    private int id;
    private String orderNumber;
    private int productId;
    private Double totalPrice;
    private Integer totalQuantity;

    public Order(){}
    public Order(int id, String orderNumber, Integer productId, Double totalPrice, Integer totalQuantity) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(int productName) {
        this.productId = productId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
