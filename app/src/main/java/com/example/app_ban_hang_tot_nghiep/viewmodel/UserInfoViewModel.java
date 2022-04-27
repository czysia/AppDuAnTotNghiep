package com.example.app_ban_hang_tot_nghiep.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.UserInfo;
import com.example.app_ban_hang_tot_nghiep.model.UserRespone;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<UserRespone> userInfor = new MutableLiveData<>();
    public MutableLiveData<Boolean> isUpdateSuccess = new MutableLiveData<>();

    public void getUser(String token) {
        mApiService = ApiUtils.getApiService();
        mApiService.getUser(token).enqueue(new Callback<UserRespone>() {
            @Override
            public void onResponse(Call<UserRespone> call, Response<UserRespone> response) {
                Log.d("TAG", "onResponse: " + response.body().getAddress());
                if (response.isSuccessful() && response.code() == 200) {
                    userInfor.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserRespone> call, Throwable t) {

            }
        });
    }

    public void updateInfoUse(MultipartBody.Part avatar, RequestBody address, RequestBody name, RequestBody phone, RequestBody token) {
        mApiService.updateInforUser(avatar, address, name, phone, token).enqueue(new Callback<UserRespone>() {
            @Override
            public void onResponse(Call<UserRespone> call, Response<UserRespone> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    userInfor.postValue(response.body());
                    isUpdateSuccess.postValue(true);
                    return;
                }
                isUpdateSuccess.postValue(false);
            }

            @Override
            public void onFailure(Call<UserRespone> call, Throwable t) {
                isUpdateSuccess.postValue(false);
            }
        });
    }
}
