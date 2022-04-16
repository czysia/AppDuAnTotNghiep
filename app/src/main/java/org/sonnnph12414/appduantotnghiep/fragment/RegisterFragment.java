package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import org.sonnnph12414.appduantotnghiep.databinding.FragmentRegisterBinding;
import org.sonnnph12414.appduantotnghiep.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    public FragmentRegisterBinding mBinding;
    public RegisterViewModel mViewModel;

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        onClick();
        mViewModel.isCreateSuccess.observe(getViewLifecycleOwner(), aBoolean -> {
            backToLogin();
            mBinding.spinKit.setVisibility(View.GONE);
            if (!aBoolean) {
                Toast.makeText(getContext(), "Register Failed", Toast.LENGTH_SHORT).show();
            } else {
                backToLogin();
                Toast.makeText(getContext(), "Register Success", Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    private void onClick() {
        mBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.spinKit.setVisibility(View.VISIBLE);
                mViewModel.createUser(mBinding.edtAccount.getText().toString(), mBinding.edtPassword.getText().toString(), mBinding.edtConfirm.getText().toString());
            }
        });

    }

    private void backToLogin() {
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().remove(this).commit();
    }
}