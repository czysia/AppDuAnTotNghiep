package org.sonnnph12414.appduantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.adapter.CategoriesAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.fragment.FoodFragment;
import org.sonnnph12414.appduantotnghiep.fragment.HomeFragment;
import org.sonnnph12414.appduantotnghiep.fragment.LoginFragment;
import org.sonnnph12414.appduantotnghiep.model.Categories;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.sonnnph12414.appduantotnghiep.R;

public class HomeActivity extends AppCompatActivity {
    public DrawerLayout drawerLayout;

    private int currentFragment=FRAGMENT_HOME;
    private  static final int FRAGMENT_HOME =0;
    private  static final int FRAGMENT_FOOD =1;
    private  static final int FRAGMENT_INFO =2;
    private  static final int FRAGMENT_SIGNOUT =3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,
                toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        FragmentManager manager = getSupportFragmentManager();
        HomeFragment homeFragment = new HomeFragment();
        manager.beginTransaction()
                .replace(R.id.content_fame, homeFragment)
                .commit();

        NavigationView navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setTitle("Trang chủ");
                        HomeFragment homeFragment1 = new HomeFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, homeFragment1)
                                .commit();
                        break;
                    case R.id.nav_food:
                        setTitle("Đồ ăn");
                        FoodFragment foodFragment = new FoodFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, foodFragment)
                                .commit();
                        break;
                    case R.id.nav_login:
                        setTitle("Đăng nhập");
                        LoginFragment loginFragment = new LoginFragment();
                        manager.beginTransaction()
                                .replace(R.id.content_fame, loginFragment)
                                .commit();
                        break;
                    case R.id.nav_sign_out:

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

}