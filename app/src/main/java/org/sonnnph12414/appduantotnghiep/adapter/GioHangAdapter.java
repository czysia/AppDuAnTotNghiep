package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.api.IimageClickListen;
import org.sonnnph12414.appduantotnghiep.model.GioHang;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHoler> {
    Context context;
    List<GioHang> giohangList;
    int soluongmoitru;

    public GioHangAdapter(List<GioHang> giohangList, Context context) {
        this.giohangList = giohangList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {

        GioHang gioHang = giohangList.get(position);
        Glide.with(context).load(gioHang.getHinhsp()).into(holder.imgGiohangimgsp);
        holder.tvGiohangtensp.setText(gioHang.getTensp());
        holder.tvGiohanggiatien.setText(String.valueOf(gioHang.getGiasp()));
        holder.tvGiohangsoluong.setText(String.valueOf(gioHang.getSoluong()));
        Double gia = gioHang.getGiasp()*gioHang.getSoluong();
        holder.tvGiohanggiatien.setText(String.valueOf(gia));
        //xu li + -
        holder.setIimageClickListen(new IimageClickListen() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                Log.e("click:","da nhan");
                if (giatri==1){
                    if (giohangList.get(pos).getSoluong()>1){
                        int soluongmoi = giohangList.get(pos).getSoluong()-1;
                        giohangList.get(pos).getSoluong(soluongmoi);
                        Log.e("click:",String.valueOf(soluongmoi));
                    }
                }else if (giatri==2){
                    if (giohangList.get(pos).getSoluong()<11){
                        int soluongmoi = giohangList.get(pos).getSoluong()+1;
                        giohangList.get(pos).getSoluong(soluongmoi);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (giohangList != null) {
            return giohangList.size();
        }
        return 0;
    }


    public class MyViewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvGiohangtensp,tvGiohanggiasp,tvGiohangsoluong,tvGiohanggiatien;
        ImageView imgGiohangimgsp,imgGiohangtru,imgGiohangcong;
        IimageClickListen iimageClickListen;

        public MyViewHoler(@NonNull View itemView) {
            super(itemView);
            tvGiohangtensp = itemView.findViewById(R.id.item_giohang_tensp);
            tvGiohanggiasp = itemView.findViewById(R.id.item_giohang_gia);
            tvGiohangsoluong = itemView.findViewById(R.id.item_giohang_soluong);
            tvGiohanggiatien = itemView.findViewById(R.id.item_giohang_giasp2);
            imgGiohangimgsp = itemView.findViewById(R.id.item_giohang_image);
            //xu li dau + -
            imgGiohangtru = itemView.findViewById(R.id.item_giohang_tru);
            imgGiohangcong = itemView.findViewById(R.id.item_giohang_cong);
            imgGiohangtru.setOnClickListener(this);
            imgGiohangcong.setOnClickListener(this);
        }

        public void setIimageClickListen(IimageClickListen iimageClickListen) {
            this.iimageClickListen = iimageClickListen;
        }

        @Override
        public void onClick(View view) {
            if (view == imgGiohangtru){
                iimageClickListen.onImageClick(view,getAdapterPosition(),1);
            }else if (view == imgGiohangcong){
                iimageClickListen.onImageClick(view,getAdapterPosition(),2);
            }
        }
    }


}
