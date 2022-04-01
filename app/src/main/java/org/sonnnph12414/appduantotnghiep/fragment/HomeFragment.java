package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.adapter.CategoriesAdapter;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.model.Categories;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    List<Categories> categoriesList;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view =inflater.inflate(R.layout.fragment_home, container, false);

        anhXa();
        ActionViewFlipper();
        categoriesList= new ArrayList<>();
        viewCategories();

        return view;
    }
    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i = 0; i<mangquangcao.size(); i++ ){
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_cut_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }



    private void anhXa() {

        viewFlipper = view.findViewById(R.id.viewlipper);
        recyclerViewManHinhChinh = view.findViewById(R.id.recycleview);
        listViewManHinhChinh = view.findViewById(R.id.listviewmanhinhchinh);
        navigationView = view.findViewById(R.id.navigationview);
        drawerLayout = view.findViewById(R.id.drawerlayout);
        //khoi tao list
        if (APIClient.manggiohang == null){APIClient.manggiohang = new ArrayList<>();}
    }
    private void viewCategories() {
        Call<List<Categories>> call = APIClient.create().getAllCategories();
        call.enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                categoriesList=response.body();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.HORIZONTAL,false);
                CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categoriesList,getActivity());
                recyclerViewManHinhChinh.setLayoutManager(gridLayoutManager);
                recyclerViewManHinhChinh.setAdapter(categoriesAdapter);

            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}