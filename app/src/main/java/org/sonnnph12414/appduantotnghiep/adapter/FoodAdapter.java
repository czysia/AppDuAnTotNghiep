package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.activity.ChiTietActivity;
import org.sonnnph12414.appduantotnghiep.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{

    List<Food> foodList;
    Context context;

    public FoodAdapter(List<Food> foodList,Context context) {
        this.foodList = foodList;
        this.context=context;

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
                if (!view.isLongClickable()){
                    Intent intent = new Intent(context, ChiTietActivity.class);
                    intent.putExtra("chitiet",food);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
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
