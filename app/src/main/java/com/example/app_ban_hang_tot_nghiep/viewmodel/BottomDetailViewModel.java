package com.example.app_ban_hang_tot_nghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomDetailViewModel extends ViewModel {
    public ApiService mApiService;
    public MutableLiveData<ResponeBill> listCart = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteSuccess = new MutableLiveData<>();
    public MutableLiveData<Boolean> completeSuccess = new MutableLiveData<>();

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

    public void receiverSuccess(String billID, String token) {
        mApiService = ApiUtils.getApiService();
        mApiService.completeBill(billID, token).enqueue(new Callback<ResponeBill>() {
            @Override
            public void onResponse(Call<ResponeBill> call, Response<ResponeBill> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    completeSuccess.postValue(true);
                    return;
                }
                completeSuccess.postValue(false);
            }

            @Override
            public void onFailure(Call<ResponeBill> call, Throwable t) {
                completeSuccess.postValue(false);
            }
        });
    }
}
