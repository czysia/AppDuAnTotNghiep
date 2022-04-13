package org.sonnnph12414.appduantotnghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import org.sonnnph12414.appduantotnghiep.ApiService;
import org.sonnnph12414.appduantotnghiep.ApiUtils;
import org.sonnnph12414.appduantotnghiep.model.Cart;
import org.sonnnph12414.appduantotnghiep.model.CartData;
import org.sonnnph12414.appduantotnghiep.model.Category;
import org.sonnnph12414.appduantotnghiep.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<List<Category>> listCate = new MutableLiveData<>();
    public MutableLiveData<List<Product>> listData = new MutableLiveData<>();
    public MutableLiveData<Cart> listCart = new MutableLiveData<>();
    public List<Product> listSearch = new ArrayList<>();

    public void getListCategory() {
        mApiService = ApiUtils.getApiService();
        mApiService.getListCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    listCate.postValue(response.body());
                    Log.d("TAG876", "onResponse: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
    }

    public void getListData() {
        mApiService = ApiUtils.getApiService();
        mApiService.getListProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    listSearch.clear();
                    listSearch.addAll(response.body());
                    listData.postValue(response.body());
                    Log.d("TAG", "onResponse: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void getListCartData(String token) {
        mApiService = ApiUtils.getApiService();
//        mApiService.getCart(token).enqueue(new Callback<Cart>() {
//            @Override
//            public void onResponse(Call<Cart> call, Response<Cart> response) {
//                Log.d("TAG456", "cart:  " + response.body());
//                if (response.isSuccessful()) {
//                    Log.d("TAG456", "cart:  " + response.body().getProducts());
//                    listCart.postValue(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Cart> call, Throwable t) {
//                Log.d("TAG456", "cart:  " + t.getMessage());
//            }
//        });

        mApiService.getCart(token).enqueue(new Callback<CartData>() {
            @Override
            public void onResponse(Call<CartData> call, Response<CartData> response) {
                Log.d("TAG111", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<CartData> call, Throwable t) {
                Log.d("TAG111", "onFailure: " + t.getMessage());
            }
        });
    }
}
