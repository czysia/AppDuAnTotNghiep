package org.sonnnph12414.appduantotnghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

import org.sonnnph12414.appduantotnghiep.adapter.CategoriesAdapter;
import org.sonnnph12414.appduantotnghiep.adapter.FoodAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.fragment.FoodFragment;
import org.sonnnph12414.appduantotnghiep.model.Categories;
import org.sonnnph12414.appduantotnghiep.model.Food;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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