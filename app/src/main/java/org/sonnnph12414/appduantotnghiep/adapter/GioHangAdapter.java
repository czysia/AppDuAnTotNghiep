package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.sonnnph12414.appduantotnghiep.model.GioHang;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHoler> {
    Context context;
    List<GioHang> giohangList;

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHoler extends RecyclerView.ViewHolder{

        public MyViewHoler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
