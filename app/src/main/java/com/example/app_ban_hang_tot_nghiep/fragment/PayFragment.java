package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.app_ban_hang_tot_nghiep.LoginActivity;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentPayBinding;
import com.example.app_ban_hang_tot_nghiep.utils.Utils;
import com.example.app_ban_hang_tot_nghiep.viewmodel.PayViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayFragment extends Fragment {

    private static final String total = "TOTALBILL";
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public onBackSuccess onBackSucessListener;
    private static int totalMoney;
    public SharedPreferences mSharedPreferences;
    private PayViewModel mPayViewModel;
    static PayFragment payFragment;

    private FragmentPayBinding mBinding;

    // TODO: Rename and change types and number of parameters
    public static PayFragment newInstance(Integer param1, onBackSuccess onListener) {
        payFragment = new PayFragment();
        Bundle args = new Bundle();
        args.putInt(total, param1);
        payFragment.setArguments(args);
        payFragment.setOnclick(onListener);
        return payFragment;
    }

    public static PayFragment getInstance() {
        return payFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            totalMoney = getArguments().getInt(total);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentPayBinding.inflate(inflater, container, false);
        mSharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        mBinding.setShipCost(new Utils().convertMoney(20000));
        mBinding.setTotalCode(new Utils().convertMoney(totalMoney));
        mPayViewModel = ViewModelProviders.of(this).get(PayViewModel.class);
        mPayViewModel.listBill.observe(getViewLifecycleOwner(), data -> {
            requireActivity().onBackPressed();
            mBinding.spinKit.setVisibility(View.GONE);
            Toast.makeText(requireContext(), "Tạo hóa đơn thành công", Toast.LENGTH_SHORT).show();
            if (onBackSucessListener != null) {
                onBackSucessListener.onSuccess();
            }
        });
        mPayViewModel.isAddSuccess.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                requireActivity().onBackPressed();
                Toast.makeText(requireContext(), "Tạo hóa đơn thành công", Toast.LENGTH_SHORT).show();
                if (onBackSucessListener != null) {
                    onBackSucessListener.onSuccess();
                }
            } else {
                Toast.makeText(requireContext(), "Tạo hóa đơn không thành công", Toast.LENGTH_SHORT).show();
            }
        });
        onClick();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private void onClick() {
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        mBinding.tvDHOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.edtEmail.getText().toString().trim().equals("") | mBinding.edtLocation.getText().toString().trim().equals("") | mBinding.edtPersonal.getText().toString().trim().equals("") | mBinding.edtPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(requireContext(), "Vui lòng không để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mSharedPreferences.getString("tokenID", "xxx").equals("xxx")) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivity(intent);
                    return;
                }
                mBinding.spinKit.setVisibility(View.VISIBLE);
                String token = mSharedPreferences.getString("tokenID", "xxx");
                mPayViewModel.addBillstData(mBinding.edtLocation.getText().toString(), mBinding.edtPhone.getText().toString(), mBinding.edtEmail.getText().toString(), mBinding.edtPersonal.getText().toString(), token);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onBackSucessListener = null;
    }

    public void setOnclick(onBackSuccess listener) {
        this.onBackSucessListener = listener;
    }

    public interface onBackSuccess {
        void onSuccess();
    }
}