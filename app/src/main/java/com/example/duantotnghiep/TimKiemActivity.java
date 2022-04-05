package com.example.duantotnghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.validation.SchemaFactory;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

//import kotlinx.coroutines.scheduling.SchedulerTimeSource;

public class TimKiemActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText edtSearch;
//    DienThoaiAdapter adapterDt;
//    List<SanPhamMoi> sanPhamMoiList;
//    ApiBanHang apiBanHang;
    RecyclerView recyclerview;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
//        initView();
//        ActionToolBar();
    }

//    private void initView() {
//        sanPhamMoiList = new ArrayList<>();
//        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
//
//        toolbar = findViewById(R.id.toolbar);
//        recyclerview = findViewById(R.id.recyclerview_search);
//        edtSearch = findViewById(R.id.edtSearch);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerview.setHasFixedSize(true);
//        recyclerview.setLayoutManager(layoutManager);
//        edtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence.length() == 0){
//                    sanPhamMoiList.clear();
//                    adapterDt = new DienThoaiAdapter(getApplicationContext(),sanPhamMoiList);
//                    recyclerview.setAdapter(adapterDt);
//                }else{
//                    getDataSearch(charSequence.toString());
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//    }
//
//    private void getDataSearch(String s) {
//        sanPhamMoiList.clear();
//        compositeDisposable.add(apiBanHang.search(s)
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(
//                sanPhamMoiModel -> {
//                    if (sanPhamMoiModel.isSuccess()){
//                        sanPhamMoiList = sanPhamMoiModel.getResult();
//                        adapterDt = new DienThoaiAdapter(getApplicationContext(),sanPhamMoiList);
//                        recyclerview.setAdapter(adapterDt);
//                    }
//                },
//                throwable -> {
//                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//        ));
//    }
//
//    private void ActionToolBar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        compositeDisposable.clear();
//        super.onDestroy();
//    }
}