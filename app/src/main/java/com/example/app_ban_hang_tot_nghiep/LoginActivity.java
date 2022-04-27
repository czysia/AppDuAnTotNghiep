package com.example.app_ban_hang_tot_nghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.app_ban_hang_tot_nghiep.databinding.ActivityLoginBinding;
import com.example.app_ban_hang_tot_nghiep.fragment.LoginFragment;
import com.example.app_ban_hang_tot_nghiep.fragment.StartFragment;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        this.getWindow().setStatusBarColor(Color.TRANSPARENT);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        gotoLoginFragment();
    }


    public void gotoLoginFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLogin, new LoginFragment()).commit();
    }

    @Override
    public void onBackPressed() {

        Fragment fragmentManager = getSupportFragmentManager().findFragmentByTag("register");
        if (fragmentManager != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentManager).commit();
            return;
        }
        Fragment fragmentRegis = getSupportFragmentManager().findFragmentByTag("forgot");
        if (fragmentRegis != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentRegis).commit();
            return;
        }

        super.onBackPressed();
    }
}