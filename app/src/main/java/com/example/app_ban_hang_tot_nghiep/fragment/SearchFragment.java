package com.example.app_ban_hang_tot_nghiep.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.CatogoryAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentSearchBinding;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements CatogoryAdapter.onItemCategoryClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentSearchBinding mBinding;
    public List<Product> mProductList = new ArrayList<>();
    private MainViewModel mViewModel;
    private CatogoryAdapter mAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentSearchBinding.inflate(inflater, container, false);
        onClick();
        setViewModel();
        setUpAdapter();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void setViewModel() {
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        mViewModel.getListData();
        mViewModel.listData.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            mProductList.clear();
            mProductList.addAll(data);
            mBinding.recycleProduct.getAdapter().notifyDataSetChanged();
        });
    }

    public void setUpAdapter() {
        mAdapter = new CatogoryAdapter(mProductList, getContext(), this);
        mBinding.recycleProduct.setAdapter(mAdapter);
        mBinding.recycleProduct.getAdapter().notifyDataSetChanged();
    }

    public void onClick() {
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null) {
                    getActivity().onBackPressed();
                }
            }
        });

        mBinding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onTextFilter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void ItemClick(Product items) {
        ArrayList arrayList = new ArrayList<String>();
        arrayList.addAll(items.getImage());
        gotaDetail(items.getId(), items.getName(), 0, items.getDetail(), 0, arrayList);
    }

    public void gotaDetail(String idProduce, String name, int prices, String des, int quality, ArrayList<String> listImage) {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new DetailProductFragment().newInstance(idProduce, name, prices, des, quality, listImage), "preview").commit();
    }

    public void onTextFilter(String text) {
        List<Product> listItem = new ArrayList<>();
        for (int i = 0; i < mProductList.size(); i++) {
            if (mProductList.get(i).getName().toLowerCase().contains(text) || mProductList.get(i).getName().toUpperCase().contains(text)) {
                listItem.add(mProductList.get(i));
            }
        }
        mAdapter.setListFilter(listItem);
    }
}