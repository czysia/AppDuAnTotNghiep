package com.example.app_ban_hang_tot_nghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> isCreateSuccess = new MutableLiveData<>();

    public void createUser(String email, String password, String repassword) {
        mApiService = ApiUtils.getApiService();
        mApiService.createInf(email, password, repassword).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    isCreateSuccess.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                isCreateSuccess.postValue(false);
            }
        });
    }
}
