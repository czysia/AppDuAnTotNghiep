package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> listData = new MutableLiveData<>();

    public void addProductToCart(String id, String token, int amount) {
        mApiService = ApiUtils.getApiService();
        mApiService.addProductToCart(id, token, amount).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    listData.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.d("TAG234", "onFailure: " + t.getMessage());
            }
        });
    }
}
