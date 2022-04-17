package com.example.app_ban_hang_tot_nghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_hang_tot_nghiep.databinding.ItemHomeProductBinding;
import com.example.app_ban_hang_tot_nghiep.databinding.ItemListProductCategoryBinding;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.utils.Utils;

import java.util.List;

public class CatogoryAdapter extends RecyclerView.Adapter<CatogoryAdapter.ViewHolder> {
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<Product> mProductList;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    private CatogoryAdapter.onItemCategoryClick onClick;

    public CatogoryAdapter(List<Product> _student, Context mContext, CatogoryAdapter.onItemCategoryClick onClick) {
        this.mProductList = _student;
        this.mContext = mContext;
        this.onClick = onClick;
    }

    @Override
    public CatogoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemListProductCategoryBinding binding = ItemListProductCategoryBinding.inflate(inflater, parent, false);

        return new CatogoryAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product data = mProductList.get(position);
        holder.onBind(data);
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.ItemClick(data);
            }
        });
    }

    public void setListFilter(List<Product> listFilter) {
        mProductList = listFilter;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListProductCategoryBinding mBinding;

        public ViewHolder(ItemListProductCategoryBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void onBind(Product items) {
            mBinding.setTitle(items.getName().toString());
            mBinding.setPrices(new Utils().convertMoney(items.getPrice()));
            mBinding.setDes(items.getPackaging().toString());
            mBinding.setUrlImage(items.getImage().get(0));
        }
    }

    public interface onItemCategoryClick {
        public void ItemClick(Product items);
    }
}
