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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.adapter.GioHangAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.api.APIClientlpm;
import org.sonnnph12414.appduantotnghiep.model.Foodbuy;
import org.sonnnph12414.appduantotnghiep.model.GioHang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GioHangFragment extends Fragment {
    TextView giohangtrong, tvSum;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;
    List<GioHang> gioHangList;

    public GioHangFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang,container,false);

        giohangtrong = view.findViewById(R.id.txtgiohangtrong);;
        recyclerView = view.findViewById(R.id.recyclerviewgiohang);
        tvSum = view.findViewById(R.id.txttongtien);
        btnmuahang = view.findViewById(R.id.btnmuahang);


        initData();
        btnmuahang.setOnClickListener(v ->{
            Fragment fragment = new ThanhToanFragment();

            Bundle bundle = new Bundle();
            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_fame, fragment, null)
                    .addToBackStack(null)
                    .commit();
        });
        //Xong - Adapter cho mang giohang
        gioHangList = new ArrayList<>();
        gioHangList = APIClient.manggiohang;
        for (int i=0;i<gioHangList.size();i++){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            GioHangAdapter gioHangAdapter = new GioHangAdapter(gioHangList,getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(gioHangAdapter);
        }
        initPostGiohang();
        return view;
    }

    private void initPostGiohang() {
        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Foodbuy foodbuy = new Foodbuy();
        APIClient.getClient("api/cart/:id");
        APIClientlpm apiClientlpm = retrofit.create(APIClientlpm.class);
        Call<Foodbuy> call=apiClientlpm.updategio("01",foodbuy.getFoodName(),foodbuy.getFoodPrice());
        call.enqueue(new Callback<Foodbuy>() {
            @Override
            public void onResponse(Call<Foodbuy> call, Response<Foodbuy> response) {
                Foodbuy foodbuy1 =response.body();
                 Toast.makeText(getContext(),"foodbuy1.getMessage()",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Foodbuy> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
            }
        });
    }

    private void initData() {
        double sum = 0;

        for (int i = 0; i < APIClient.manggiohang.size(); i++) {
            int quantity = APIClient.manggiohang.get(i).getSoluong();
            double price = APIClient.manggiohang.get(i).getGiasp();
            sum += quantity * price;
            // Log.d("AAA", "Gia: " + price + "; So luong: " + quantity);
        }

        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance("VND"));

        tvSum.setText(format.format(sum));
        //POST retrofit
//        Foodbuy foodbuy = new Foodbuy();
//        APIClient.getClient("api/cart/:id");
//        APIClientlpm apiClientlpm = retrofit.create(APIClientlpm.class);
//        Call<Foodbuy> call=apiClientlpm.updategio(foodbuy.getFoodName(),String.valueOf(price),String.valueOf(sum));
//        call.enqueue(new Callback<Foodbuy>() {
//            @Override
//            public void onResponse(Call<Foodbuy> call, Response<Foodbuy> response) {
//                Foodbuy foodbuy1 =response.body();
//                 Toast.makeText(getContext(),"foodbuy1.getMessage()",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<Foodbuy> call, Throwable t) {
//                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });

    }

}
