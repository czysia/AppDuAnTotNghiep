package org.sonnnph12414.appduantotnghiep.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.sonnnph12414.appduantotnghiep.model.GioHang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "https://flying-blossom-cerise.glitch.me/";

    public static Retrofit retrofit = null;

    public static APIClientlpm create() {
        return getClient(BASE_URL).create(APIClientlpm.class);
    }

    public static APIClientlpm create1() {
        return getClient("").create(APIClientlpm.class);
    }

    public static Retrofit getClient(String url) {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit LoginAPI(){
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    //tao bien toan cuc cho phan gio hang
    public static ArrayList<GioHang> manggiohang;
}
