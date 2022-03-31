package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.activity.ChiTietActivity;
import org.sonnnph12414.appduantotnghiep.activity.GioHangActivity;
import org.sonnnph12414.appduantotnghiep.fragment.ChiTietFragment;
import org.sonnnph12414.appduantotnghiep.fragment.GioHangFragment;
import org.sonnnph12414.appduantotnghiep.model.Food;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    List<Food> foodList;
    Context context;

    public FoodAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Food food = foodList.get(position);
        if (food == null) {
            return;
        }
        Glide.with(context).load(food.getImage().get(0)).into(holder.imgFood);
        holder.tvFoodName.setText(food.getName());
        holder.tvFoodPrice.setText(food.getPrice());
        holder.tvFoodInfo.setText(food.getIngredients());
        //click vào ảnh
        holder.imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                 chua chuyen du lieu + mo fragment moi
                if (!view.isLongClickable()){
                    Intent intent = new Intent(context, ChiTietActivity.class);
                    Fragment fragment = new ChiTietFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("chitietName", holder.tvFoodName.getText().toString());
                    bundle.putString("chitietPrice", holder.tvFoodPrice.getText().toString());
                    bundle.putString("chitietInfo", holder.tvFoodInfo.getText().toString());
                    bundle.putString("imgUrl", food.getImage().get(0));
                    fragment.setArguments(bundle);

                    Log.e("send","da chuyen");

                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_fame, fragment, null)
                            .addToBackStack(null)
                            .commit();

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (foodList != null) {
            return foodList.size();
        }
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView tvFoodName,tvFoodPrice,tvFoodInfo;
        ImageView imgFood;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvFoodName);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
            tvFoodInfo = itemView.findViewById(R.id.tvFoodInfo);
            imgFood = itemView.findViewById(R.id.imgFood);

        }
    }

}
