package org.sonnnph12414.appduantotnghiep.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ThanhToan implements Serializable {
    @SerializedName("_id")
    private String Id;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("date")
    private String date;
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
    private Object transporter;
    @SerializedName("start_at")
    private Object startAt;
    @SerializedName("finish_at")
    private Object finishAt;
    @SerializedName("feedback")
    private String feedback;
    @SerializedName("__v")
    private int _v;


    public void setId(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return this.Id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAddress() {
        return this.userAddress;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return this.total;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setBillStatus(boolean billStatus) {
        this.billStatus = billStatus;
    }

    public boolean getBillStatus() {
        return this.billStatus;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getVerifier() {
        return this.verifier;
    }

    public void setTransporter(Object transporter) {
        this.transporter = transporter;
    }

    public Object getTransporter() {
        return this.transporter;
    }

    public void setStartAt(Object startAt) {
        this.startAt = startAt;
    }

    public Object getStartAt() {
        return this.startAt;
    }

    public void setFinishAt(Object finishAt) {
        this.finishAt = finishAt;
    }

    public Object getFinishAt() {
        return this.finishAt;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public void set_v(int _v) {
        this._v = _v;
    }

    public int get_v() {
        return this._v;
    }


//    public static ThanhToan create(String json) {
//        Gson gson = new GsonBuilder().create();
//        return gson.fromJson(json, ThanhToan.class);
//    }
//
//    public String toString() {
//        Gson gson = new GsonBuilder().create();
//        return gson.toJson(this);
//    }

}