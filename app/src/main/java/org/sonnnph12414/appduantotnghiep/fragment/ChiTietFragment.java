package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;
import org.sonnnph12414.appduantotnghiep.model.GioHang;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietFragment extends Fragment implements View.OnClickListener {
    TextView tvName, tvPrice, tvDescription;
    Button btnAddToCart;
    ImageView imageView;
    Spinner spinner;
    Toolbar toolbar;
    Double price;
    Foodbuy foodbuy;
    String name, imgUrl;

    public ChiTietFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiet,container,false);

        tvName = view.findViewById(R.id.txttensanpham);
        tvPrice = view.findViewById(R.id.txtgiasanpham);
        tvDescription = view.findViewById(R.id.txtmotachitiet);
        imageView = view.findViewById(R.id.imgchitiet);
//       spinner = view.findViewById(R.id.spinner);
        // toolbar = view.findViewById(R.id.toolbar)
        btnAddToCart = view.findViewById(R.id.btnthemvaogiohang);
        btnAddToCart.setOnClickListener(this);

        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("chitietName");
            price = Double.parseDouble(bundle.getString("chitietPrice"));
            String Info = bundle.getString("chitietInfo");
            imgUrl = bundle.getString("imgUrl");
            Glide.with(getContext()).load(imgUrl).into(imageView);


            tvName.setText(name);
            tvDescription.setText(Info);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvPrice.setText("Giá: "+decimalFormat.format(price)+"đ");

        }

    }


    @Override
    public void onClick(View view) {
        // da chuyen du lieu sang gio hang
        Fragment fragment = new GioHangFragment();

        Bundle bundle = new Bundle();
        bundle.putString("name", tvName.getText().toString());
        bundle.putDouble("price", price);
        bundle.putInt("quantity", 1);

        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fame, fragment, null)
                .addToBackStack(null)
                .commit();
        themgiohang();
    }

    private void themgiohang() {
        int quantity = 1;
        GioHang item = new GioHang();
        item.setTensp(name);
        item.setGiasp(price);
        item.setSoluong(quantity);
        item.setHinhsp(imgUrl);

        APIClient.manggiohang.add(item);

//        List<GioHang> manggioHangs = new ArrayList<GioHang>();
//
//        Bundle bundle = getArguments();
////        if (APIClient.manggiohang.size()>0){
////        }else {
//            foodbuy = new Foodbuy(bundle.getString("chitietName"),bundle.getString("chitietPrice"),
//                    bundle.getString("chitietInfo"),bundle.getString("imgUrl"));
//            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
//            long gia = foodbuy.getFoodPrice()*soluong;
//            GioHang gioHang = new GioHang();
//            gioHang.setTensp(foodbuy.getFoodName());
//            gioHang.setGiasp(foodbuy.getFoodPrice());
//            gioHang.getSoluong(soluong);
//            gioHang.getGiasp(gia);
//            manggioHangs.add(gioHang);
////        }
//
//        Fragment fragment = new GioHangFragment();
//        Bundle bundle2 = new Bundle();
//        bundle2.putParcelableArrayList("manggiohang", (ArrayList<? extends Parcelable>) manggioHangs);
//        fragment.setArguments(bundle);
//
//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.content_fame, fragment, null)
//                .addToBackStack(null)
//                .commit();
    }
}
