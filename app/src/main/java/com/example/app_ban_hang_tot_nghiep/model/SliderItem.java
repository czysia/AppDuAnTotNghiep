package com.example.app_ban_hang_tot_nghiep.model;

public class SliderItem {
    private String description;
    private String imageUrl;

    public SliderItem(String description, String url) {
        this.description = description;
        this.imageUrl = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
