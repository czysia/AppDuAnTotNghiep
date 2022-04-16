package org.sonnnph12414.appduantotnghiep.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import org.sonnnph12414.appduantotnghiep.ApiService;
import org.sonnnph12414.appduantotnghiep.ApiUtils;
import org.sonnnph12414.appduantotnghiep.MainActivity;
import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.databinding.FragmentLoginBinding;
import org.sonnnph12414.appduantotnghiep.viewmodel.LoginViewModel;

public class LoginFragment extends Fragment {

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
                Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
            } else {
                requireActivity().finish();
                Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
            }
        });
        mViewModel.token.observe(getViewLifecycleOwner(), token -> {
            mSharedPreferences.putString("tokenID", token.token).apply();
        });
        mViewModel.isVerify.observe(getViewLifecycleOwner(), data -> {
            if (data) {
                Toast.makeText(requireContext(), "Bạn cần xác nhận thông tin qua mail trước khi đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick() {
        mLoginBinding.btnLogin.setOnClickListener(view -> {
            mLoginBinding.spinKit.setVisibility(View.VISIBLE);
            mViewModel.logIn(mLoginBinding.edtAccount.getText().toString(), mLoginBinding.edtPassword.getText().toString());
        });
        mLoginBinding.btnRegister.setOnClickListener(view -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager.beginTransaction().add(R.id.containerLogin, new RegisterFragment(), "register").commit();
        });
    }
}