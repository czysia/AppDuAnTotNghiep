package com.example.app_ban_hang_tot_nghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_hang_tot_nghiep.databinding.ItemHomeProductBinding;
import com.example.app_ban_hang_tot_nghiep.databinding.ItemListCartBinding;
import com.example.app_ban_hang_tot_nghiep.model.ItemCartMoreInfo;
import com.example.app_ban_hang_tot_nghiep.model.ItemProductCartItem;
import com.example.app_ban_hang_tot_nghiep.model.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<ItemCartMoreInfo> mCartList;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    private CartAdapter.onItemClick onClick;

    public CartAdapter(List<ItemCartMoreInfo> mCart, Context mContext, CartAdapter.onItemClick onClick) {
        this.mCartList = mCart;
        this.mContext = mContext;
        this.onClick = onClick;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemListCartBinding binding = ItemListCartBinding.inflate(inflater, parent, false);

        return new CartAdapter.ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        ItemCartMoreInfo data = mCartList.get(position);
        holder.onBind(data);
        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick.ItemClick(data);
            }
        });
        holder.mBinding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClick.onLongClick(data);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListCartBinding mBinding;

        public ViewHolder(ItemListCartBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void onBind(ItemCartMoreInfo items) {
            mBinding.setPrices(items.getPrice() + "");
            mBinding.setUrlImage(items.getImage());
            mBinding.setTitle(items.getProductName());
            mBinding.setDes(items.getAmount() + "");
//            mBinding.setUrlImage(items.getImage().get(0));
        }
    }

    public interface onItemClick {
        public void ItemClick(ItemCartMoreInfo items);

        public void onLongClick(ItemCartMoreInfo items);
    }
}
