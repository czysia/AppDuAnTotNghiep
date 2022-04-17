package com.example.app_ban_hang_tot_nghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayViewModel extends ViewModel {

    public ApiService mApiService;
    public MutableLiveData<ResponeBill> listBill = new MutableLiveData<>();

    public void addBillstData(String address, String phone, String mail, String name, String token) {
        mApiService = ApiUtils.getApiService();

        mApiService.addBills(address, phone, mail, name, token).enqueue(new Callback<ResponeBill>() {
            @Override
            public void onResponse(Call<ResponeBill> call, Response<ResponeBill> response) {
                if (response.isSuccessful()) {
                    listBill.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponeBill> call, Throwable t) {

            }
        });
    }
}
