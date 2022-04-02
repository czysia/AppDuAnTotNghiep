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
    @SerializedName("username")
    private String username;
    @SerializedName("user_address")
    private String userAddress;
    @SerializedName("total")
    private int total;
    @SerializedName("payment_type")
    private String paymentType;
    @SerializedName("payment_status")
    private boolean paymentStatus;
    @SerializedName("bill_status")
    private boolean billStatus;
    @SerializedName("verifier")
    private String verifier;
    @SerializedName("transporter")
    private String transporter;
    @SerializedName("start_at")
    private String startAt;
    @SerializedName("finish_at")
    private String finishAt;
    @SerializedName("feedback")
    private String feedback;
    @SerializedName("__v")
    private int _v;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean isBillStatus() {
        return billStatus;
    }

    public void setBillStatus(boolean billStatus) {
        this.billStatus = billStatus;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(String finishAt) {
        this.finishAt = finishAt;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int get_v() {
        return _v;
    }

    public void set_v(int _v) {
        this._v = _v;
    }

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
