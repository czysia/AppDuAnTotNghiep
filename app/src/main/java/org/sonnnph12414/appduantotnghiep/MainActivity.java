package org.sonnnph12414.appduantotnghiep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.sonnnph12414.appduantotnghiep.fragment.FoodFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FoodFragment())
                .commit();
    }
}