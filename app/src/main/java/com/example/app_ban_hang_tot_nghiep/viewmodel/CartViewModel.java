package com.example.app_ban_hang_tot_nghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Cart;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {

    public ApiService mApiService;
    public MutableLiveData<Cart> listCart = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteSuccess = new MutableLiveData<>();

    public void getListCartData(String token) {
        mApiService = ApiUtils.getApiService();

        mApiService.getCart(token).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    listCart.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
            }
        });
    }

    public void deleteItemCart(String idProduct, String token) {
        mApiService = ApiUtils.getApiService();

        mApiService.deleteItem(idProduct, token).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    listCart.postValue(response.body());
                    deleteSuccess.postValue(true);
                    return;
                }
                deleteSuccess.postValue(false);
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                deleteSuccess.postValue(false);
            }
        });
    }
}
