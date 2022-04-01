package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.adapter.FoodAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.model.Food;
import org.sonnnph12414.appduantotnghiep.model.ThanhToan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanFragment extends Fragment {
    List<ThanhToan> thanhToans;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        viewThanhToan();
        return  view;
    }

    private void viewThanhToan() {
        Call<ThanhToan> call = APIClient.create().getAllThanhtoan("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyM2Y2ZGY4MDUxMjE0Mjg5NDIzMjUxZCIsImVtYWlsIjoic29ubnZwaDIzMzczQGZwdC5lZHUudm4iLCJpYXQiOjE2NDg3MDE0NTl9.kzr8X7UNfkva6vUul37OH7YcMsPwsjE8OIv-x39yiuc");
        call.enqueue(new Callback<ThanhToan>() {
            @Override
            public void onResponse(Call<ThanhToan> call, Response<ThanhToan> response) {
                ThanhToan  thanhtoan =response.body();
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
//                FoodAdapter foodAdapter = new FoodAdapter(thanhToans, getActivity());
//                rcvFood.setLayoutManager(linearLayoutManager);
//                rcvFood.setAdapter(foodAdapter);
//  pman ch t tao xem api
            }

            @Override
            public void onFailure(Call<ThanhToan> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
}