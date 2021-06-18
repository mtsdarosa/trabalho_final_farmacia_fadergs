package com.example.appcupom;

public class Cart {
    private int id;
    private Integer productId;
    private Double totalPrice = 0.00;
    private Integer totalQuantity = 0;

    public Cart(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = this.totalPrice + totalPrice;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = this.totalQuantity + totalQuantity;
    }
}
