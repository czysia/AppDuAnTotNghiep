package org.sonnnph12414.appduantotnghiep.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.adapter.FoodAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.model.Food;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {

    List<Food> foodList;
    RecyclerView rcvFood;

    public FoodFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_food, container, false);

        rcvFood =view.findViewById(R.id.rcvFood);

        foodList= new ArrayList<>();

        viewFood();
        return view;
    }

    private void viewFood() {
        Call<List<Food>> call = APIClient.create().getAllFood();
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foodList=response.body();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                FoodAdapter foodAdapter = new FoodAdapter(foodList, getActivity());
                rcvFood.setLayoutManager(linearLayoutManager);
                rcvFood.setAdapter(foodAdapter);

            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}