package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ban_hang_tot_nghiep.databinding.ItemHomeProductBinding;
import com.example.app_ban_hang_tot_nghiep.model.ItemProductCartItem;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<ItemProductCartItem> mCartList;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    private onItemClick onClick;

    public CartAdapter(List<ItemProductCartItem> mCart, Context mContext, onItemClick onClick) {
        this.mCartList = mCart;
        this.mContext = mContext;
        this.onClick = onClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemHomeProductBinding binding = ItemHomeProductBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Product data = mProductList.get(position);
//        holder.onBind(data);
//        holder.mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClick.ItemClick(data);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemHomeProductBinding mBinding;

        public ViewHolder(ItemHomeProductBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void onBind(ItemProductCartItem items) {
            mBinding.tvPrices.setText(items.getPrice() + "");
//            mBinding.setUrlImage(items.getImage().get(0));
        }
    }

    public interface onItemClick {
        public void ItemClick(ItemProductCartItem items);
    }
}
