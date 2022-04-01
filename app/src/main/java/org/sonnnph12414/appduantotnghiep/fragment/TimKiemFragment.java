package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sonnnph12414.appduantotnghiep.R;

public class TimKiemFragment extends Fragment {
//        Toolbar toolbar;
//    EditText edtSearch;
//    DienThoaiAdapter adapterDt;
//    List<SanPhamMoi> sanPhamMoiList;
//    ApiBanHang apiBanHang;
//    RecyclerView recyclerview;
//    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        initView();
//        ActionToolBar();
        return inflater.inflate(R.layout.fragment_tim_kiem, container, false);
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
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        sanPhamMoiModel -> {
//                            if (sanPhamMoiModel.isSuccess()){
//                                sanPhamMoiList = sanPhamMoiModel.getResult();
//                                adapterDt = new DienThoaiAdapter(getApplicationContext(),sanPhamMoiList);
//                                recyclerview.setAdapter(adapterDt);
//                            }
//                        },
//                        throwable -> {
//                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                ));
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