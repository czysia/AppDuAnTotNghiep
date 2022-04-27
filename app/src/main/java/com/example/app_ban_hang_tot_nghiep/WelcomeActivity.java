package com.example.app_ban_hang_tot_nghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.example.app_ban_hang_tot_nghiep.databinding.ActivityWelcomeBinding;
import com.example.app_ban_hang_tot_nghiep.fragment.StartFragment;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding mWelcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        this.getWindow().setStatusBarColor(Color.TRANSPARENT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMain();
            }
        }, 3000);
    }

    public void gotoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoStartFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.containerLayout, new StartFragment()).commit();
    }
}