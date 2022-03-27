package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.model.Categories;
import org.sonnnph12414.appduantotnghiep.model.Food;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder>{

    List<Categories> categoriesList;
    Context context;

    public CategoriesAdapter(List<Categories> categoriesList, Context context) {
        this.categoriesList = categoriesList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories categories = categoriesList.get(position);
        if (categories == null) {
            return;
        }
        Glide.with(context).load(categories.getImage()).into(holder.imgCategories);
        holder.tvCategoriesName.setText(categories.getName());


    }

    @Override
    public int getItemCount() {
        if (categoriesList != null) {
            return categoriesList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoriesName,tvCategoriesPrice;
        ImageView imgCategories;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoriesName = itemView.findViewById(R.id.tvCategoriesName);
            imgCategories = itemView.findViewById(R.id.imgCategories);


        }
    }

}
