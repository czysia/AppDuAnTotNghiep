package com.example.app_ban_hang_tot_nghiep.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.CartAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.LayoutDetailBillBinding;
import com.example.app_ban_hang_tot_nghiep.model.ItemCartMoreInfo;
import com.example.app_ban_hang_tot_nghiep.model.ResponeBill;
import com.example.app_ban_hang_tot_nghiep.utils.Utils;
import com.example.app_ban_hang_tot_nghiep.viewmodel.BottomDetailViewModel;
import com.example.app_ban_hang_tot_nghiep.viewmodel.CartViewModel;
import com.example.app_ban_hang_tot_nghiep.viewmodel.MainViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment implements CartAdapter.onItemClick {

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    private LayoutDetailBillBinding mBinding;
    private BottomSheetBehavior mSheetBehavior;
    private CartAdapter mAdapter;
    private MainViewModel mViewModel;
    private BottomDetailViewModel mDetailViewModel;
    public List<ItemCartMoreInfo> mListData = new ArrayList<>();
    private ResponeBill dataBill;
    private String iD;
    String token;
    SharedPreferences sharedPreferences;

    @Override
    public void ItemClick(ItemCartMoreInfo items) {

    }

    @Override
    public void onLongClick(ItemCartMoreInfo items) {

    }

    public static BottomSheetFragment newInstance(ResponeBill data) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("info", data);
        bottomSheetFragment.setArguments(bundle);
        return bottomSheetFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        LayoutInflater layoutInflater = LayoutInflater.from(requireContext());
        mBinding = LayoutDetailBillBinding.inflate(layoutInflater, null, false);
        dialog.setContentView(mBinding.getRoot());
        sharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("tokenID", "xxx");
        mSheetBehavior = BottomSheetBehavior.from((View) mBinding.getRoot().getParent());
        mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        onClick();
        dataBill = (ResponeBill) getArguments().getSerializable("info");
        setUpViewModel();
        setupData();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog sheetDialog = (BottomSheetDialog) dialog;
                setupFullHeight(sheetDialog);
            }
        });

        // Do something with your dialog like setContentView() or whatever
        return dialog;
    }

    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        ConstraintLayout bottomSheet = bottomSheetDialog.findViewById(R.id.ctlDetail);
        BottomSheetBehavior behavior;
        if (bottomSheet != null) {
            behavior = BottomSheetBehavior.from((View) bottomSheet.getParent());
            ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
            int windowHeight = getWindowHeight();
            if (layoutParams != null) {
                layoutParams.height = windowHeight;
            }
            bottomSheet.setLayoutParams(layoutParams);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) requireContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private void onClick() {
        mBinding.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mBinding.btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.spinKit.setVisibility(View.VISIBLE);
                mDetailViewModel.receiverSuccess(iD, token);
            }
        });

        mBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.spinKit.setVisibility(View.VISIBLE);
                mDetailViewModel.deleteListBill(iD, token);
            }
        });
    }

    private void setupData() {
        if (mViewModel.listSearch.size() > 0) {
            List<ItemCartMoreInfo> listItem = new ArrayList<>();
            for (int i = 0; i < dataBill.getProducts().size(); i++) {
                Log.d("TAG", "setupData: " + dataBill.getProducts().get(i).getProductId());
                for (int j = 0; j < mViewModel.listSearch.size(); j++) {
                    Log.d("TAG", "search: " + mViewModel.listSearch.get(j).getId());
                    if (dataBill.getProducts().get(i).getProductId().equals(mViewModel.listSearch.get(j).getId())) {
                        ItemCartMoreInfo itemCart = new ItemCartMoreInfo();
                        itemCart.setAmount(dataBill.getProducts().get(i).getAmount());
                        itemCart.setPrice(dataBill.getProducts().get(i).getPrice());
                        itemCart.setImage(mViewModel.listSearch.get(j).getImage().get(0));
                        itemCart.setProductName(dataBill.getProducts().get(i).getProductName());
                        itemCart.setProductId(dataBill.getProducts().get(i).getProductId());
                        listItem.add(itemCart);
                    }
                }
                mListData.clear();
                mListData.addAll(listItem);
                mAdapter = new CartAdapter(mListData, getContext(), this);
                mBinding.recycleOrder.setAdapter(mAdapter);
                mBinding.recycleOrder.getAdapter().notifyDataSetChanged();
                mBinding.tvCodeShow.setText(dataBill.getId());
                iD = dataBill.getId();
                mBinding.tvNameCustomer.setText(dataBill.getUsername());
                Log.d("TAG", "setupData: " + dataBill.isBillStatus() + dataBill.isPaymentStatus() + dataBill.isTransporting());
                if (dataBill.isBillStatus() && dataBill.isPaymentStatus() && dataBill.isTransporting()) {
                    mBinding.tvStatusBill.setText("Đã nhận và thanh toán hàng");
                    mBinding.btnSuccess.setVisibility(View.GONE);
                    mBinding.btnCancel.setVisibility(View.GONE);
                } else if (dataBill.isBillStatus() && dataBill.isTransporting()) {
                    mBinding.tvStatusBill.setText("Đang giao hàng");
                    mBinding.btnCancel.setVisibility(View.GONE);
                } else if (dataBill.isBillStatus()) {
                    mBinding.tvStatusBill.setText("Đã xác nhận");
                    mBinding.btnCancel.setVisibility(View.GONE);
                } else if (!dataBill.isBillStatus()) {
                    mBinding.tvStatusBill.setText("Đang chờ xác nhận");
                    mBinding.btnSuccess.setVisibility(View.GONE);
                } else {
                    mBinding.tvStatusBill.setText("Đang giao hàng");
                    mBinding.btnCancel.setVisibility(View.GONE);
                }
                mBinding.tvMoneyCount.setText(new Utils().convertMoney(dataBill.getTotal()));
            }
        }
    }

    public void setUpViewModel() {
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mDetailViewModel = ViewModelProviders.of(this).get(BottomDetailViewModel.class);
        mDetailViewModel.completeSuccess.observe(this, data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(requireContext(), "Xác nhận đơn hàng thành công", Toast.LENGTH_SHORT).show();
                Bundle result = new Bundle();
                result.putString("bundleKey", "result");
                getParentFragmentManager().setFragmentResult("requestKey", result);
                dismiss();
            } else {
                Toast.makeText(requireContext(), "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });

        mDetailViewModel.deleteSuccess.observe(this, data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(requireContext(), "Xóa đơn hàng thành công", Toast.LENGTH_SHORT).show();
                Bundle result = new Bundle();
                result.putString("bundleKey", "result");
                getParentFragmentManager().setFragmentResult("requestKey", result);
                dismiss();
            } else {
                Toast.makeText(requireContext(), "Đã xảy ra lỗi vui lòng thử lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setUpAdapter() {
        mAdapter = new CartAdapter(mListData, getContext(), this);
        mBinding.recycleOrder.setAdapter(mAdapter);
    }
}
