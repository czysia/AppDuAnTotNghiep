package com.example.app_ban_hang_tot_nghiep;

public class ApiUtils {
    public static final String BASE_URL = "https://flying-blossom-cerise.glitch.me/";

    public static ApiService getApiService() {
        return RetrofitUtils.getClient(BASE_URL).create(ApiService.class);
    }
}
