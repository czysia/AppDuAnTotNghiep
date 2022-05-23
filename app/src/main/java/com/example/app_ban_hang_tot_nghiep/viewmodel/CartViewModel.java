package com.example.app_ban_hang_tot_nghiep.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.model.Cart;
import com.example.app_ban_hang_tot_nghiep.model.ChangeCartParams;
import com.example.app_ban_hang_tot_nghiep.model.ItemProductCart;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class CartViewModel extends ViewModel {

    public ApiService mApiService;
    public MutableLiveData<Cart> listCart = new MutableLiveData<>();
    public MutableLiveData<Boolean> updateData = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteSuccess = new MutableLiveData<>();

    public void getListCartData(String token) {
        mApiService = ApiUtils.getApiService();

        mApiService.getCart(token).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful() && response.code() == 200) {
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

    public void updateCart(String token, List<ItemProductCart> list) {
        mApiService = ApiUtils.getApiService();
        mApiService.updateCart(new ChangeCartParams(token, list)).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    updateData.postValue(true);
                    return;
                }
                updateData.postValue(false);
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                updateData.postValue(false);
            }
        });
    }
}
