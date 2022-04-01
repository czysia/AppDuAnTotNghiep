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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.sonnnph12414.appduantotnghiep.R;

import java.text.DecimalFormat;

public class ChiTietFragment extends Fragment implements View.OnClickListener {
    TextView tvName, tvPrice, tvDescription;
    Button btnAddToCart;
    ImageView imageView;
    Spinner spinner;
    Toolbar toolbar;

    Double price;

    public ChiTietFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chitiet,container,false);

        tvName = view.findViewById(R.id.txttensanpham);
        tvPrice = view.findViewById(R.id.txtgiasanpham);
        tvDescription = view.findViewById(R.id.txtmotachitiet);
        imageView = view.findViewById(R.id.imgchitiet);
        spinner = view.findViewById(R.id.spinner);
        // toolbar = view.findViewById(R.id.toolbar)
        btnAddToCart = view.findViewById(R.id.btnthemvaogiohang);
        btnAddToCart.setOnClickListener(this);

        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String Name = bundle.getString("chitietName");
            price = Double.parseDouble(bundle.getString("chitietPrice"));
            String Info = bundle.getString("chitietInfo");
            String imgUrl = bundle.getString("imgUrl");

            Glide.with(getContext()).load(imgUrl).into(imageView);

            tvName.setText(Name);
            tvDescription.setText(Info);
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            tvPrice.setText("Giá: "+decimalFormat.format(price)+"đ");
            Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
            ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(getContext(),R.layout.support_simple_spinner_dropdown_item,soluong);
            spinner.setAdapter(spinnerAdapter);
        }

    }


    @Override
    public void onClick(View view) {
        Fragment fragment = new GioHangFragment();

        Bundle bundle = new Bundle();
        bundle.putString("name", tvName.getText().toString());
        bundle.putDouble("price", price);
        bundle.putInt("quantity", Integer.parseInt(spinner.getSelectedItem().toString()));

        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_fame, fragment, null)
                .addToBackStack(null)
                .commit();
    }
}
