package org.sonnnph12414.appduantotnghiep;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import org.sonnnph12414.appduantotnghiep.databinding.ActivityLoginBinding;
import org.sonnnph12414.appduantotnghiep.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        gotoLoginFragment();
    }


    public void gotoLoginFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLogin, new LoginFragment()).commit();
    }
}