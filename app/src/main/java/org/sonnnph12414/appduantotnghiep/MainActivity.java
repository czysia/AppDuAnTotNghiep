package org.sonnnph12414.appduantotnghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import org.sonnnph12414.appduantotnghiep.fragment.FoodFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    ListView listViewManHinhChinh;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

    }

    private void Anhxa() {
        toolbar =findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper =findViewById(R.id.viewflipper);
        recyclerViewManHinhChinh =findViewById(R.id.recyclerview);
        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView =findViewById(R.id.navigationview);
    }
}