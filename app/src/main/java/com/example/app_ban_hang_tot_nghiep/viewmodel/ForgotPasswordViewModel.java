package com.example.app_ban_hang_tot_nghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> isRegisterSuccess = new MutableLiveData<>();
    public MutableLiveData<String> isNewPass = new MutableLiveData<>();

    public void forgotPassWord(String email) {
        mApiService = ApiUtils.getApiService();
        mApiService.forgotPass(email).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    isNewPass.postValue(response.body());
                    isRegisterSuccess.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                isRegisterSuccess.postValue(false);
            }
        });
    }
}
