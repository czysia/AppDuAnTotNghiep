package com.example.app_ban_hang_tot_nghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.app_ban_hang_tot_nghiep.databinding.ActivityWelcomeBinding;
import com.example.app_ban_hang_tot_nghiep.fragment.StartFragment;

public class WelcomeActivity extends AppCompatActivity {

    private ActivityWelcomeBinding mWelcomeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWelcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
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