package org.sonnnph12414.appduantotnghiep.api;


import org.sonnnph12414.appduantotnghiep.model.Categories;
import org.sonnnph12414.appduantotnghiep.model.Food;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;
import org.sonnnph12414.appduantotnghiep.model.ResponseUpdate;
import org.sonnnph12414.appduantotnghiep.model.ThanhToan;
import org.sonnnph12414.appduantotnghiep.model.User;

import java.util.BitSet;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIClientlpm {

    @GET("api/products/getall")
    Call<List<Food>> getAllFood();

    @GET("api/bills/get")
    Call<Categories> getAllThanhtoan(@Query("token") String token);
    @POST("api/bills/add")
    Call<Categories> datHang(@Query("token") String token);

    @GET("api/categories/getall")
    Call<List<Categories>> getAllCategories();

    @POST("api/users/create")
    @FormUrlEncoded
    Call<ResponseUpdate> dangky(
            @Field("id") String id,
            @Field("email") String email,
            @Field("password") String password,
            @Field("repassword") String repassword
    );

    @FormUrlEncoded
    @POST("api/users/login")
        @Headers({
                "Accept: application/json",
                "Content-Type: application/x-www-form-urlencoded",
        })
    Call<ResponseBody> getToken(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("api/users/forgot/password")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded",
    })
    Call<ResponseBody> QuenMK(
            @Field("email") String email
    );

    //https://flying-blossom-cerise.glitch.me/api/cart/:id
    @FormUrlEncoded
    @POST("/api/cart/:id")
    Call<Foodbuy> updategio(
            @Field("id") String id,
            @Field("Price") String Name,
            @Field("Sum") String Price
    );

}
