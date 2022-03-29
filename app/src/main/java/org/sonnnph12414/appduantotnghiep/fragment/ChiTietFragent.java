package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.model.Food;

import java.text.DecimalFormat;

public class ChiTietFragent extends Fragment {
    TextView tensp,giasp,mota;
    Button btnthem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;

    public ChiTietFragent(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_chitiet,container,false);

        tensp = view.findViewById(R.id.txttensanpham);
        giasp = view.findViewById(R.id.txtgiasanpham);
        mota = view.findViewById(R.id.txtmotachitiet);
        btnthem = view.findViewById(R.id.btnthemvaogiohang);
        spinner = view.findViewById(R.id.spinner);
        imghinhanh = view.findViewById(R.id.imgchitiet);
        toolbar = view.findViewById(R.id.toolbar);


        initData();

        return view;
    }

    private void initData() {
//        Food food = (Food) getIntent().getSerializableExtra("chitiet");
//        tensp.setText(food.getName());
//        mota.setText(food.getOrigin());
//        Glide.with(getApplicationContext()).load(food.getImage()).into(imghinhanh);
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        giasp.setText("Giá: "+decimalFormat.format(Double.parseDouble(food.getPrice()))+"đ");
//        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
//        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,so);
//        spinner.setAdapter(adapterspin);
    }


}
