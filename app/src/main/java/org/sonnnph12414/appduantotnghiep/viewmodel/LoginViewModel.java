package org.sonnnph12414.appduantotnghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.sonnnph12414.appduantotnghiep.ApiService;
import org.sonnnph12414.appduantotnghiep.ApiUtils;
import org.sonnnph12414.appduantotnghiep.model.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<Boolean> isLoginSucess = new MutableLiveData<>();
    public MutableLiveData<Token> token = new MutableLiveData<>();
    public MutableLiveData<Boolean> isVerify = new MutableLiveData<>();

    public void logIn(String email, String password) {
        mApiService = ApiUtils.getApiService();
        mApiService.getAnswers(email, password).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful() && response.body().token != null) {
                    isLoginSucess.postValue(true);
                    token.postValue(response.body());
                    return;
                }
                isVerify.postValue(true);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                isLoginSucess.postValue(false);
            }
        });
    }
}
