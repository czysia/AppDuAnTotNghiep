package org.sonnnph12414.appduantotnghiep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Categories {
    @SerializedName("_id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("detail")
    @Expose
    String detail;
    @SerializedName("postion")
    @Expose
    String postion;
    @SerializedName("image")
    @Expose
    String image;

    public Categories() {
    }

    public Categories(String id, String name, String detail, String postion, String image) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.postion = postion;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
