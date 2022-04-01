package org.sonnnph12414.appduantotnghiep.activity;

import static org.sonnnph12414.appduantotnghiep.R.id.container;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;


import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.fragment.FoodFragment;
import org.sonnnph12414.appduantotnghiep.fragment.HomeFragment;
import org.sonnnph12414.appduantotnghiep.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FoodFragment())
                .commit();
    }
}