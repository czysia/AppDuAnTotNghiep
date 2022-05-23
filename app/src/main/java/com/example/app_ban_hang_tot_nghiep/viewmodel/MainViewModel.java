package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Category;
import com.example.app_ban_hang_tot_nghiep.model.DetailProduct;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<List<Category>> listCate = new MutableLiveData<>();
    public MutableLiveData<List<Product>> listData = new MutableLiveData<>();
    public MutableLiveData<List<Product>> listHomeData = new MutableLiveData<>();
    public MutableLiveData<List<DetailProduct>> listFavourite = new MutableLiveData<>();
    public MutableLiveData<ResponeBill> listBill = new MutableLiveData<>();
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

    public void getListHomeData() {
        mApiService = ApiUtils.getApiService();
        mApiService.getListProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    listSearch.clear();
                    listSearch.addAll(response.body());
                    listHomeData.postValue(response.body());
                    Log.d("TAG", "onResponse: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public void getListFavourite(String token) {
        mApiService = ApiUtils.getApiService();
        mApiService.getFavorite(token).enqueue(new Callback<List<DetailProduct>>() {
            @Override
            public void onResponse(Call<List<DetailProduct>> call, Response<List<DetailProduct>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    listFavourite.postValue(response.body());
                    Log.d("TAG", "onResponse: " + response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<DetailProduct>> call, Throwable t) {

            }
        });
    }
}
