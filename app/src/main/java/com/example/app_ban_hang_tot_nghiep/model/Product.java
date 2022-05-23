package com.example.app_ban_hang_tot_nghiep.model;

import java.util.List;

public class Product {
    private List<String> image;
    private String origin;
    private int V;
    private String name;
    private String certificate;
    private String ingredients;
    private String warning;
    private String _id;
    private String preserve;
    private String source;
    private String detail;
    private String category;

    public List<String> getImage() {
        return image;
    }

    public String getOrigin() {
        return origin;
    }

    public int getV() {
        return V;
    }

    public String getName() {
        return name;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getWarning() {
        return warning;
    }

    public String getId() {
        return _id;
    }

    public String getPreserve() {
        return preserve;
    }

    public String getSource() {
        return source;
    }

    public String getDetail() {
        return detail;
    }

    public String getCategory() {
        return category;
    }
}