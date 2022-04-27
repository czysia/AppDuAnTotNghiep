package com.example.app_ban_hang_tot_nghiep;

import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.CartData;
import com.example.app_ban_hang_tot_nghiep.model.Category;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;
import com.example.app_ban_hang_tot_nghiep.model.Token;
import com.example.app_ban_hang_tot_nghiep.model.UserInfo;
import com.example.app_ban_hang_tot_nghiep.model.UserRespone;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @FormUrlEncoded
    @POST("api/users/login")
    Call<Token> getAnswers(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("api/users/forgot/password")
    Call<String> forgotPass(@Field("email") String email);

    @FormUrlEncoded
    @POST("api/users/change/password")
    Call<String> changePass(@Field("token") String token, @Field("oldPassword") String oldPass, @Field("newPassword") String newPass);

    @FormUrlEncoded
    @POST("api/users/create")
    Call<UserInfo> createInf(@Field("email") String email, @Field("password") String password, @Field("repassword") String repassword);

    @GET("api/products/getall")
    Call<List<Product>> getListProducts();

    @GET("api/categories/getall")
    Call<List<Category>> getListCategory();

    @GET("api/products/category/{idCategory}")
    Call<List<Product>> getListProductWithCate(@Path("idCategory") String id);

    @FormUrlEncoded
    @POST("api/cart/add/{idProduct}")
    Call<Cart> addProductToCart(@Path("idProduct") String productId, @Field("token") String token, @Field("amount") int amount);

    @GET("api/cart/getcart/{token}")
    Call<Cart> getCart(@Path("token") String tokenUser);

    @FormUrlEncoded
    @POST("api/cart/delete/{productID}")
    Call<Cart> deleteItem(@Path("productID") String productID, @Field("token") String tokenUser);

    @FormUrlEncoded
    @POST("api/bills/add/{address}/{phone}/{email}/{name}")
    Call<ResponeBill> addBills(@Path("address") String address, @Path("phone") String phone, @Path("email") String email, @Path("name") String name, @Field("token") String token);

    @GET("api/bills/get/{token}")
    Call<List<ResponeBill>> getBill(@Path("token") String token);

    @FormUrlEncoded
    @POST("api/bills/cancel")
    Call<String> deleteBill(@Field("bill_id") String billID, @Field("token") String token);

    @FormUrlEncoded
    @POST("api/bills/complete/{bill_id}")
    Call<ResponeBill> completeBill(@Path("bill_id") String bill_id, @Field("token") String token);

    @GET("api/users/{token}")
    Call<UserRespone> getUser(@Path("token") String token);

    @Multipart
    @POST("api/users/update/info")
    Call<UserRespone> updateInforUser(@Part MultipartBody.Part stringAvatar,
                                      @Part("address") RequestBody address,
                                      @Part("name") RequestBody name,
                                      @Part("phone") RequestBody phone,
                                      @Part("token") RequestBody token);
}
