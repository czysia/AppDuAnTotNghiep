package org.sonnnph12414.appduantotnghiep.fragment;

import static org.sonnnph12414.appduantotnghiep.api.APIClient.retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.api.APIClientlpm;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;

import java.text.NumberFormat;
import java.util.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GioHangFragment extends Fragment {
    TextView giohangtrong, tvSum;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;

    public GioHangFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang,container,false);

        giohangtrong = view.findViewById(R.id.txtgiohangtrong);
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recyclerviewgiohang);
        tvSum = view.findViewById(R.id.txttongtien);
        btnmuahang = view.findViewById(R.id.btnmuahang);

        //return super.onCreateView(inflater, container, savedInstanceState);

        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        int quantity = bundle.getInt("quantity");
        double price = bundle.getDouble("price");
        double sum = price * quantity;

        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("VND"));

        tvSum.setText(format.format(sum));
        //POST retrofit
        Foodbuy foodbuy = new Foodbuy();
        APIClient.getClient("api/cart/:id");
        APIClientlpm apiClientlpm = retrofit.create(APIClientlpm.class);
        Call<Foodbuy> call=apiClientlpm.updategio(foodbuy.getFoodName(),String.valueOf(price),String.valueOf(sum));
        call.enqueue(new Callback<Foodbuy>() {
            @Override
            public void onResponse(Call<Foodbuy> call, Response<Foodbuy> response) {
                Foodbuy foodbuy1 =response.body();
                Toast.makeText(getContext(),foodbuy1.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Foodbuy> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

}
