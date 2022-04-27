package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.MainActivity;
import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.BillAdapter;
import com.example.app_ban_hang_tot_nghiep.adapter.CartAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentMyBillBinding;
import com.example.app_ban_hang_tot_nghiep.model.Product;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;
import com.example.app_ban_hang_tot_nghiep.viewmodel.BillWaitingViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyBillFragment extends Fragment implements BillAdapter.onItemCategoryClick, SwipeRefreshLayout.OnRefreshListener {

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private FragmentMyBillBinding mBinding;
    private BillWaitingViewModel mViewModel;
    private BillAdapter mAdapter;

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mViewModel.getListBillData(token);
                    mBinding.refreshData.setRefreshing(false);
                } catch (Exception ex) {
                    mBinding.refreshData.setRefreshing(false);
                }
            }
        }, 2500);
    }

    SharedPreferences sharedPreferences;
    String token;
    public List<ResponeBill> listData = new ArrayList<>();

    // TODO: Rename and change types and number of parameters
    public static MyBillFragment newInstance(String param1, String param2) {
        MyBillFragment fragment = new MyBillFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                mViewModel.getListBillData(token);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentMyBillBinding.inflate(inflater, container, false);
        sharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("tokenID", "xxx");
        setUpAdapter();
        setUpViewModel();
        mBinding.refreshData.setOnRefreshListener(this);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void ItemClick(ResponeBill items) {
        BottomSheetFragment bottomSheetFragment = BottomSheetFragment.newInstance(items);
        bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void itemLongClickListener(ResponeBill items) {
//        new AlertDialog.Builder(requireContext()).setMessage(R.string.delete_bill)
//                .setTitle(R.string.delete_bill_title)
//                .setPositiveButton(R.string.yes, (arg0, arg1) -> {
////                    mBinding.spinKit.setVisibility(View.VISIBLE);
//                    Log.d("TAG", "itemLongClickListener: " + items.getId() + "-----" + token);
////                    mViewModel.deleteListBill(items.getId(), token);
//                })
//                .setNegativeButton(R.string.no, (arg0, arg1) -> {
//
//                })
//                .show();
    }

    private void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(BillWaitingViewModel.class);
        mViewModel.getListBillData(token);
        mViewModel.listBill.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            listData.clear();
            listData.addAll(data);
            mBinding.recycleBill.getAdapter().notifyDataSetChanged();
        });
        mViewModel.deleteSuccess.observe(getViewLifecycleOwner(), data -> {
            if (data) {
                Toast.makeText(requireContext(), "Xóa hóa đơn thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Xóa hóa đơn thất bại", Toast.LENGTH_SHORT).show();
            }
        });
        onClick();
    }

    public void setUpAdapter() {
        mAdapter = new BillAdapter(listData, requireContext(), this);
        mBinding.recycleBill.setAdapter(mAdapter);
    }

    public void onClick() {
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    public void showDialog() {

    }
}