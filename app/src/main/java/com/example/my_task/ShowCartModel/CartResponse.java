package com.example.my_task.ShowCartModel;

public class CartResponse {
    private int id;
    private String userId,date;
    //private ProductArray products;

    public CartResponse() {
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

//    public ProductArray getProducts() {
//        return products;
//    }
//
//    public void setProducts(ProductArray products) {
//        this.products = products;
//    }
}
