package com.example.app_ban_hang_tot_nghiep.model;

public class ItemCartMoreInfo {
    private int amount;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    private String image;
    private int price;
    private String productId;
    private String productName;

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }
}
