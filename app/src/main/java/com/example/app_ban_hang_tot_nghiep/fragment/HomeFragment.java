package com.example.app_ban_hang_tot_nghiep.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.app_ban_hang_tot_nghiep.MainActivity;
import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.HomeAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentHomeBinding;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import me.farahani.spaceitemdecoration.SpaceItemDecoration;

public class HomeFragment extends Fragment implements HomeAdapter.onItemClick, SwipeRefreshLayout.OnRefreshListener {

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
        mBinding.refreshData.setOnRefreshListener(this);
        setUpAdapter();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void setUpAdapter() {
        int spanCount = 2; // 3 columns
        int spacing = 30; // 50px
        boolean includeEdge = true;
        mHomeAdapter = new HomeAdapter(mProductList, getContext(), this);
        mBinding.recycleHome.setAdapter(mHomeAdapter);
        mBinding.recycleHome.addItemDecoration(new SpaceItemDecoration(spacing, includeEdge));
    }

    @Override
    public void ItemClick(Product items) {
        ArrayList<String> listUrl = new ArrayList<>();
        listUrl.addAll(items.getImage());
        gotaDetail(items.getId(), items.getName(), items.getPrice(), items.getDetail(), items.getQuantily(), listUrl);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mViewModel.getListHomeData();
                    ((MainActivity) requireActivity()).refreshData();
                    mBinding.refreshData.setRefreshing(false);
                } catch (Exception ex) {
                    mBinding.refreshData.setRefreshing(false);
                }
            }
        }, 2500);
    }

    public void gotaDetail(String idProduce, String name, int prices, String description, int quality, ArrayList<String> listImage) {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new DetailProductFragment().newInstance(idProduce, name, prices, description, quality, listImage), "preview").commit();
    }
}