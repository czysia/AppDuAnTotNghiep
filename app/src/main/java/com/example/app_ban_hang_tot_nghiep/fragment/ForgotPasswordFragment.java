package com.example.app_ban_hang_tot_nghiep.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentForgotPasswordBinding;
import com.example.app_ban_hang_tot_nghiep.viewmodel.ForgotPasswordViewModel;
import com.example.app_ban_hang_tot_nghiep.viewmodel.LoginViewModel;

public class ForgotPasswordFragment extends Fragment {

    private FragmentForgotPasswordBinding mBinding;
    private ForgotPasswordViewModel mViewModel;

    // TODO: Rename and change types and number of parameters
    public static ForgotPasswordFragment newInstance(String param1, String param2) {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentForgotPasswordBinding.inflate(inflater, container, false);
        setUpViewModel();
        onClick();
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void onClick() {
        mBinding.btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.edtEmail.getText().toString().trim().equals("") | !mBinding.edtEmail.getText().toString().trim().contains("@")) {
                    Toast.makeText(requireContext(), "Định dạng không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                mViewModel.forgotPassWord(mBinding.edtEmail.getText().toString().trim());
            }
        });

        mBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
    }

    public void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(ForgotPasswordViewModel.class);
        mViewModel.isNewPass.observe(getViewLifecycleOwner(), data -> {
//            mBinding.edtEmail.setText(data);
        });
        mViewModel.isRegisterSuccess.observe(getViewLifecycleOwner(), data -> {
            if (!data) {
                Toast.makeText(requireContext(), "Thay đổi mật khẩu không thành công bạn vui lòng kiểm tra email để biết mật khẩu mới", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}