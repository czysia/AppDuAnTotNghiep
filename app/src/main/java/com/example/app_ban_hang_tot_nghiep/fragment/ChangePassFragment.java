package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentChangePassBinding;
import com.example.app_ban_hang_tot_nghiep.viewmodel.ChangPassViewModel;


public class ChangePassFragment extends Fragment {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private FragmentChangePassBinding mBinding;
    private ChangPassViewModel mViewModel;
    String token;
    SharedPreferences sharedPreferences;

    public ChangePassFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ChangePassFragment newInstance(String param1, String param2) {
        ChangePassFragment fragment = new ChangePassFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentChangePassBinding.inflate(inflater, container, false);
        setUpViewModel();
        onClick();
        sharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("tokenID", "xxx");
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(ChangPassViewModel.class);
        mViewModel.isSuccess.observe(getViewLifecycleOwner(), data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(requireContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Thay đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClick() {
        mBinding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.edtPassword.getText().toString().trim().equals("") ||
                        mBinding.edtConfirm.getText().toString().trim().equals("") ||
                        mBinding.editOldPass.getText().toString().trim().equals("") ||
                        !mBinding.edtPassword.getText().toString().trim().equals(mBinding.edtConfirm.getText().toString().trim())) {
                    Toast.makeText(requireContext(), "Thông tin nhập chưa chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    mBinding.spinKit.setVisibility(View.VISIBLE);
                    mViewModel.getListData(token, mBinding.editOldPass.getText().toString().trim(), mBinding.edtPassword.getText().toString().trim());
                }
            }
        });
    }
}