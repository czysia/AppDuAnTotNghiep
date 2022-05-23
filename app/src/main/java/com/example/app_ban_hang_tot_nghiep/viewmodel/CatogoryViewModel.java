package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatogoryViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<List<Product>> listData = new MutableLiveData<>();

    public void getListData(String idCate) {
        mApiService = ApiUtils.getApiService();
        mApiService.getListProductWithCate(idCate).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    Log.d("TAG", "onResponse: cate " + response.body().size());
                    listData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
