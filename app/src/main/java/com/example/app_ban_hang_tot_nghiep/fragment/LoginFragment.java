package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.ApiService;
import com.example.app_ban_hang_tot_nghiep.ApiUtils;
import com.example.app_ban_hang_tot_nghiep.MainActivity;
import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentLoginBinding;
import com.example.app_ban_hang_tot_nghiep.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

    public static final int RESULT_CODE_SUCCESS = 6688;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public FragmentLoginBinding mLoginBinding;
    public SharedPreferences.Editor mSharedPreferences;
    public ApiService mApiService;
    public LoginViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        mApiService = ApiUtils.getApiService();
        mSharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        setupViewModel();
        onClick();
        // Inflate the layout for this fragment
        return mLoginBinding.getRoot();
    }

    private void gotoMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    public void setupViewModel() {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mViewModel.isLoginSucess.observe(getViewLifecycleOwner(), aBoolean -> {
            mLoginBinding.spinKit.setVisibility(View.GONE);
            if (!aBoolean) {
                Toast.makeText(getContext(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
            } else {
                requireActivity().setResult(RESULT_CODE_SUCCESS);
                requireActivity().finish();
                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }
        });
        mViewModel.token.observe(getViewLifecycleOwner(), token -> {
            mSharedPreferences.putString("tokenID", token.token).apply();
        });
        mViewModel.isVerify.observe(getViewLifecycleOwner(), data -> {
            mLoginBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(requireContext(), "Bạn cần xác nhận thông tin qua mail trước khi đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
        mViewModel.accountNotSuccess.observe(getViewLifecycleOwner(), data -> {
            mLoginBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(requireContext(), "Tài khoản không chính xác hoặc bạn chưa xác nhận đăng kí trong email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick() {
        mLoginBinding.btnLogin.setOnClickListener(view -> {
            if (mLoginBinding.edtAccount.getText().toString().trim().equals("") | mLoginBinding.edtPassword.getText().toString().trim().equals("")) {
                Toast.makeText(requireContext(), "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            mLoginBinding.spinKit.setVisibility(View.VISIBLE);
            mViewModel.logIn(mLoginBinding.edtAccount.getText().toString().trim(), mLoginBinding.edtPassword.getText().toString().trim());
        });
        mLoginBinding.btnRegister.setOnClickListener(view -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction().add(R.id.containerLogin, new RegisterFragment(), "register").commit();
        });

        mLoginBinding.btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().add(R.id.containerLogin, new ForgotPasswordFragment(), "forgot").commit();
            }
        });
    }
}