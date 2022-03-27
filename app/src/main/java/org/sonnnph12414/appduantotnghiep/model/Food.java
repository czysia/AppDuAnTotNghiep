package org.sonnnph12414.appduantotnghiep.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Food implements Serializable {
    @SerializedName("image")
    @Expose
    ArrayList<String> image;
    @SerializedName("_id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("import_price")
    @Expose
    String import_price;
    @SerializedName("price")
    @Expose
    String price;
    @SerializedName("ingredients")
    @Expose
    String ingredients;
    @SerializedName("weight")
    @Expose
    String weight;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("preserve")
    @Expose
    String preserve;
    @SerializedName("source")
    @Expose
    String source;
    @SerializedName("origin")
    @Expose
    String origin;
    @SerializedName("quantily")
    @Expose
    String quantily;
    @SerializedName("category")
    @Expose
    String category;

    public Food() {
    }

    public Food(ArrayList<String> image, String id, String name, String import_price, String price, String ingredients, String weight, String date, String preserve, String source, String origin, String quantily, String category) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.import_price = import_price;
        this.price = price;
        this.ingredients = ingredients;
        this.weight = weight;
        this.date = date;
        this.preserve = preserve;
        this.source = source;
        this.origin = origin;
        this.quantily = quantily;
        this.category = category;
    }

    public ArrayList<String> getImage() {
        return image;
    }

    public void setImage(ArrayList<String> image) {
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

    public String getImport_price() {
        return import_price;
    }

    public void setImport_price(String import_price) {
        this.import_price = import_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreserve() {
        return preserve;
    }

    public void setPreserve(String preserve) {
        this.preserve = preserve;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getQuantily() {
        return quantily;
    }

    public void setQuantily(String quantily) {
        this.quantily = quantily;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
