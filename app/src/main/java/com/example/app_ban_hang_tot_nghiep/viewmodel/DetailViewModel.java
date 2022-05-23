package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.DetailProduct;
import com.example.app_ban_hang_tot_nghiep.model.ResponseAddFavourite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> listData = new MutableLiveData<>();
    public MutableLiveData<Boolean> addDataSuccess = new MutableLiveData<>();
    public MutableLiveData<List<DetailProduct>> listDetail = new MutableLiveData<>();

    public void addProductToCart(String id, String token, int amount) {
        mApiService = ApiUtils.getApiService();
        mApiService.addProductToCart(id, token, amount).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Log.d("TAG", "resultCoe: " + response.code());
                if (response.isSuccessful() && response.code() == 200) {
                    listData.postValue(true);
                    return;
                }
                listData.postValue(false);
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.d("TAG234", "onFailure: " + t.getMessage());
                listData.postValue(false);
            }
        });
    }

    public void getDetailProduct(String id) {
        mApiService = ApiUtils.getApiService();
        mApiService.getListDetailProduct(id).enqueue(new Callback<List<DetailProduct>>() {
            @Override
            public void onResponse(Call<List<DetailProduct>> call, Response<List<DetailProduct>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Log.d("TAG", "onResponse: " + response.body().size());
                    listDetail.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DetailProduct>> call, Throwable t) {
                Log.d("TAG", "faild: " + t.getMessage());
            }
        });
    }

    public void addToFavourite(String token, String variID) {
        mApiService = ApiUtils.getApiService();
        mApiService.addFavourite(token, variID).enqueue(new Callback<ResponseAddFavourite>() {
            @Override
            public void onResponse(Call<ResponseAddFavourite> call, Response<ResponseAddFavourite> response) {
                Log.d("TAG345", "onResponse: " + response.isSuccessful() + response.code());
                if (response.isSuccessful() && response.code() == 200) {
                    addDataSuccess.postValue(true);
                    return;
                }
                addDataSuccess.postValue(false);
            }

            @Override
            public void onFailure(Call<ResponseAddFavourite> call, Throwable t) {
                Log.d("TAG345", "onResponse: " + t.getMessage());
                addDataSuccess.postValue(false);
            }
        });
    }
}
