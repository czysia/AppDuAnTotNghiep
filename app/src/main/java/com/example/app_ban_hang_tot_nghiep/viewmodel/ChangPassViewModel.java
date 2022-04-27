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

public class ChangPassViewModel extends ViewModel {
    public ApiService mApiService;

    public MutableLiveData<Boolean> isSuccess = new MutableLiveData<>();

    public void getListData(String token, String oldPass, String newPass) {
        mApiService = ApiUtils.getApiService();
        mApiService.changePass(token, oldPass, newPass).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("TAG345", "success:  " + response.code());
                if (response.isSuccessful() && response.code() == 200) {
                    isSuccess.postValue(true);
                    return;
                }
                isSuccess.postValue(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Log.d("TAG345", "onFailure:  }" + t.getMessage());
                isSuccess.postValue(false);
            }
        });
    }
}
