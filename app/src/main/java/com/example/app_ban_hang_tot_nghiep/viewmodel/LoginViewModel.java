package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> isLoginSucess = new MutableLiveData<>();
    public MutableLiveData<Boolean> accountNotSuccess = new MutableLiveData<>();
    public MutableLiveData<Token> token = new MutableLiveData<>();
    public MutableLiveData<Boolean> isVerify = new MutableLiveData<>();

    public void logIn(String email, String password) {
        mApiService = ApiUtils.getApiService();
        mApiService.getAnswers(email, password).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    isLoginSucess.postValue(true);
                    token.postValue(response.body());
                } else if (response.code() == 404) {
                    accountNotSuccess.postValue(true);
                } else {
                    isVerify.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                isLoginSucess.postValue(false);
            }
        });
    }
}
