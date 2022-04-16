package org.sonnnph12414.appduantotnghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.sonnnph12414.appduantotnghiep.ApiService;
import org.sonnnph12414.appduantotnghiep.ApiUtils;
import org.sonnnph12414.appduantotnghiep.model.UserInfo;

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
