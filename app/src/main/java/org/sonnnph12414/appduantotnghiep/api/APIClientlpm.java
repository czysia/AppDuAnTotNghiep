package org.sonnnph12414.appduantotnghiep.api;


import org.sonnnph12414.appduantotnghiep.model.Categories;
import org.sonnnph12414.appduantotnghiep.model.Food;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;
import org.sonnnph12414.appduantotnghiep.model.ResponseUpdate;
import org.sonnnph12414.appduantotnghiep.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIClientlpm {

    @GET("api/products/getall")
    Call<List<Food>> getAllFood();

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

//https://flying-blossom-cerise.glitch.me/api/cart/:id
    @FormUrlEncoded
    @POST("/api/cart/:id")
    Call<Foodbuy> updategio(
            @Field("id") String id,
            @Field("Price") String Name,
            @Field("Sum") String Price
    );
}
