package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillWaitingViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<List<ResponeBill>> listBill = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteSuccess = new MutableLiveData<>();

    public void getListBillData(String token) {
        mApiService = ApiUtils.getApiService();
        List<ResponeBill> listData = new ArrayList<>();

        mApiService.getBill(token).enqueue(new Callback<List<ResponeBill>>() {
            @Override
            public void onResponse(Call<List<ResponeBill>> call, Response<List<ResponeBill>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (response.body() != null) {
                        listData.clear();
                        listData.addAll(response.body());
                        listBill.postValue(listData);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResponeBill>> call, Throwable t) {

            }
        });

//        mApiService.getBill(token).enqueue(new Callback<ResponeBill>() {
//            @Override
//            public void onResponse(Call<ResponeBill> call, Response<ResponeBill> response) {
//                if (response.isSuccessful() && response.code() == 200) {
//                    if (response.body() != null) {
//                        listData.clear();
//                        listData.add(response.body());
//                        listBill.postValue(listData);
//                    }
//                }
//            }

//            @Override
//            public void onFailure(Call<ResponeBill> call, Throwable t) {
//
//            }
//        });
    }

    public void deleteListBill(String billID, String token) {
        mApiService = ApiUtils.getApiService();

        mApiService.deleteBill(billID, token).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    deleteSuccess.postValue(true);
                    return;
                }
                deleteSuccess.postValue(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                deleteSuccess.postValue(false);
            }
        });
    }
}
