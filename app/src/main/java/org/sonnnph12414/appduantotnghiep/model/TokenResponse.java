package org.sonnnph12414.appduantotnghiep.model;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("token")
    private String token;

    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
