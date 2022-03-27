package org.sonnnph12414.appduantotnghiep.api;


import org.sonnnph12414.appduantotnghiep.model.Categories;
import org.sonnnph12414.appduantotnghiep.model.Food;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIClientlpm {

    @GET("api/products/getall")
    Call<List<Food>> getAllFood();

    @GET("api/categories/getall")
    Call<List<Categories>> getAllCategories();
}
