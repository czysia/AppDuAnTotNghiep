package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.fragment.FoodFragment;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FoodFragment())
                .commit();
    }
}