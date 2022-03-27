package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.sonnnph12414.appduantotnghiep.R;

public class SplashScreenActivity extends AppCompatActivity {
    public static int SPLASH_TIME = 3000; //This is 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashScreen = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(splashScreen);
                finish();
            }
        }, SPLASH_TIME);
    }
}