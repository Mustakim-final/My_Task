package com.example.my_task.CartModel;

import java.util.List;

public class ProductModel {
    private int id;
    private String userId,date;

    ProductCartModel products;

    public ProductModel() {
    }

    public ProductModel(int id, String userId, String date, ProductCartModel products) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ProductCartModel getProducts() {
        return products;
    }

    public void setProducts(ProductCartModel products) {
        this.products = products;
    }
}
