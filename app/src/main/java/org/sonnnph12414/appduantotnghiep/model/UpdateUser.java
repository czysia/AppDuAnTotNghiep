package org.sonnnph12414.appduantotnghiep.model;

import org.sonnnph12414.appduantotnghiep.api.APIClientlpm;
import org.sonnnph12414.appduantotnghiep.api.ConstUpdate;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateUser {
    public void updateUserFn(String tvResult, User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstUpdate.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2. Dua du lieu can update vao request
        RequestUpdate requestUpdate = new RequestUpdate();
        requestUpdate.setUser(user);
        //3.goi ham interface
        APIClientlpm apiClientl = retrofit.create(APIClientlpm.class);
        Call<ResponseUpdate> call = apiClientl.dangky(user.getId(), user.getEmail(), user.getPassword(), user.getRepassword());
        //4.thuc thi
        call.enqueue(new Callback<ResponseUpdate>() {
            @Override
            public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                //get data
                ResponseUpdate responseUpdate = response.body();
                    tvResult.endsWith(responseUpdate.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                tvResult.endsWith(t.getMessage());
            }
        });
    }
}
