package com.example.app_ban_hang_tot_nghiep.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.HomeAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentHomeBinding;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.view.decor.GridSpacingItemDecoration;
import com.example.app_ban_hang_tot_nghiep.viewmodel.HomeViewModel;
import com.example.app_ban_hang_tot_nghiep.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeAdapter.onItemClick {

    public MainViewModel mViewModel;
    public FragmentHomeBinding mBinding;
    public HomeAdapter mHomeAdapter;
    public List<Product> mProductList = new ArrayList<>();

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        mViewModel.getListHomeData();
        mViewModel.listHomeData.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            mProductList.clear();
            mProductList.addAll(data);
            mBinding.recycleHome.getAdapter().notifyDataSetChanged();
        });
        setUpAdapter();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void setUpAdapter() {
        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        mHomeAdapter = new HomeAdapter(mProductList, getContext(), this);
        mBinding.recycleHome.setAdapter(mHomeAdapter);
        mBinding.recycleHome.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

    @Override
    public void ItemClick(Product items) {
        ArrayList<String> listUrl = new ArrayList<>();
        listUrl.addAll(items.getImage());
        gotaDetail(items.getId(), items.getName(), items.getPrice(), items.getPackaging(), listUrl);
    }

    public void gotaDetail(String idProduce, String name, int prices, String description, ArrayList<String> listImage) {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new DetailProductFragment().newInstance(idProduce, name, prices, description, listImage), "preview").commit();
    }
}