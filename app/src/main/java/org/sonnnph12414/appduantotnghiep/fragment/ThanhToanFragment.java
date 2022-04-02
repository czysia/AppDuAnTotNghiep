package org.sonnnph12414.appduantotnghiep.fragment;

 import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.activity.SuccessActivity;
import org.sonnnph12414.appduantotnghiep.adapter.CategoriesAdapter;
import org.sonnnph12414.appduantotnghiep.adapter.FoodAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.model.Categories;
import org.sonnnph12414.appduantotnghiep.model.Food;
import org.sonnnph12414.appduantotnghiep.model.ThanhToan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhToanFragment extends Fragment {
    List<ThanhToan> thanhToans;
    TextView totalAmout;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        totalAmout = view.findViewById(R.id.totalAmout);
        button = view.findViewById(R.id.dathang);
        viewThanhToan();
        button.setOnClickListener(v -> {
            Call<Categories> call = APIClient.create().datHang("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyM2Y2ZGY4MDUxMjE0Mjg5NDIzMjUxZCIsImVtYWlsIjoic29ubnZwaDIzMzczQGZwdC5lZHUudm4iLCJpYXQiOjE2NDg4ODc0OTV9.SXssuN4r-nXs0nALMHrfB98RZ7fQ2Ei9gD3biHiwylg");
            call.enqueue(new Callback<Categories>() {
                @Override
                public void onResponse(Call<Categories> call, Response<Categories> response) {
                    if (response.body() != null) {
                        Intent intent = new Intent(getActivity(), SuccessActivity.class);
                        startActivity(intent);
                    }



                }

                @Override
                public void onFailure(Call<Categories> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        });
        return view;
    }

    private void viewThanhToan() {
        Call<Categories> call = APIClient.create().getAllThanhtoan("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyM2Y2ZGY4MDUxMjE0Mjg5NDIzMjUxZCIsImVtYWlsIjoic29ubnZwaDIzMzczQGZwdC5lZHUudm4iLCJpYXQiOjE2NDg4ODc0OTV9.SXssuN4r-nXs0nALMHrfB98RZ7fQ2Ei9gD3biHiwylg");
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.body() != null) {
                    Categories categories = response.body();
                    totalAmout.setText("Tong tien :\t" + categories.getTotal());

                }


            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}