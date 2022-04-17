package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;

public class Cart {
    private int total;
    private String userId;
    private int V;
    private String _id;
    private List<ItemProductCartItem> products;

    public int getTotal() {
        return total;
    }

    public String getUserId() {
        return userId;
    }

    public int getV() {
        return V;
    }

    public String getId() {
        return _id;
    }

    public List<ItemProductCartItem> getProducts() {
        return products;
    }
}