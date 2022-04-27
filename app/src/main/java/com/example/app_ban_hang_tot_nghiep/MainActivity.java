package com.example.app_ban_hang_tot_nghiep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.adapter.ExpandableListAdapter;
import com.example.app_ban_hang_tot_nghiep.adapter.SliderAdapterExample;
import com.example.app_ban_hang_tot_nghiep.databinding.ActivityMainBinding;
import com.example.app_ban_hang_tot_nghiep.fragment.CartFragment;
import com.example.app_ban_hang_tot_nghiep.fragment.CatorogyCommonFragment;
import com.example.app_ban_hang_tot_nghiep.fragment.ChangePassFragment;
import com.example.app_ban_hang_tot_nghiep.fragment.HomeFragment;
import com.example.app_ban_hang_tot_nghiep.fragment.MyBillFragment;
import com.example.app_ban_hang_tot_nghiep.fragment.SearchFragment;
import com.example.app_ban_hang_tot_nghiep.model.Category;
import com.example.app_ban_hang_tot_nghiep.model.MenuModel;
import com.example.app_ban_hang_tot_nghiep.model.SliderItem;
import com.example.app_ban_hang_tot_nghiep.viewmodel.MainViewModel;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public ActivityMainBinding mBinding;
    public MainViewModel mViewModel;
    public ExpandableListAdapter expandableListAdapter;
    private SharedPreferences mSharedPreferences;
    List<Category> headerList = new ArrayList<>();
    List<String> listDemo = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    private SliderAdapterExample adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        this.getWindow().setStatusBarColor(Color.TRANSPARENT);
        setUpViewModel();
        mBinding.navigationView.setNavigationItemSelectedListener(this);
        addHome();
        setUpSlider();
        populateExpandableList();

        mSharedPreferences = this.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String token = mSharedPreferences.getString("tokenID", "xxx");
        if (!token.equals("xxx")) {
            mBinding.setIsLogin(true);
        } else {
            mBinding.setIsLogin(false);
        }

//        addNewItem();
        renewItems();
        onClick();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
        } else if (id == R.id.information) {
            Intent intent = new Intent(this, InforUserActivity.class);
            startActivity(intent);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.parent_content, new CatorogyCommonFragment().newInstance(id + "", id + ""), "catetory").commit();
        }
        mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }

        Fragment fragmentChange = getSupportFragmentManager().findFragmentByTag("changepass");
        if (fragmentChange != null) {
            Log.d("TAG", "onBackPressed:  " + fragmentChange);
            getSupportFragmentManager().beginTransaction().remove(fragmentChange).commit();
            return;
        }

        Fragment fragmentManager = getSupportFragmentManager().findFragmentByTag("preview");
        if (fragmentManager != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentManager).commit();
            return;
        }
        Fragment fragmentSearch = getSupportFragmentManager().findFragmentByTag("search");
        if (fragmentSearch != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentSearch).commit();
            return;
        }

        Fragment fragmentBill = getSupportFragmentManager().findFragmentByTag("billwaiting");
        if (fragmentBill != null) {
            getSupportFragmentManager().beginTransaction().remove(fragmentBill).commit();
            return;
        }

        Fragment fragmentCaterory = getSupportFragmentManager().findFragmentByTag("catetory");
        if (fragmentCaterory != null) {
            Log.d("TAG", "onBackPressed:  " + fragmentCaterory);
            getSupportFragmentManager().beginTransaction().remove(fragmentCaterory).commit();
            return;
        }

        Fragment fragmentPay = getSupportFragmentManager().findFragmentByTag("pay");
        if (fragmentPay != null) {
            Log.d("TAG", "onBackPressed:  " + fragmentPay);
            getSupportFragmentManager().beginTransaction().remove(fragmentPay).commit();
            return;
        }

        Fragment fragmentCart = getSupportFragmentManager().findFragmentByTag("cart");
        if (fragmentCart != null) {
            Log.d("TAG", "onBackPressed:  " + fragmentCart);
            getSupportFragmentManager().beginTransaction().remove(fragmentCart).commit();
            return;
        }

        super.onBackPressed();
    }

    public void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.getListCategory();
        mViewModel.listCate.observe(this, data -> {
            headerList.clear();
            headerList.addAll(data);
            expandableListAdapter.notifyDataSetChanged();
        });
    }

    void prepareMenuData() {
    }

    private void populateExpandableList() {
        expandableListAdapter = new ExpandableListAdapter(this, headerList, new HashMap<>());
        mBinding.expandableListView.setAdapter(expandableListAdapter);

        mBinding.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.parent_content, new CatorogyCommonFragment().newInstance(headerList.get(groupPosition).getName(), headerList.get(groupPosition).getId() + ""), "catetory").commit();
                mBinding.drawerLayout.closeDrawers();
                return false;
            }
        });

        mBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                return false;
            }
        });
    }

    private void onClick() {
        mBinding.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawers();
                Intent intent = new Intent(view.getContext(), InforUserActivity.class);
                startActivity(intent);
            }
        });
        mBinding.incController.imgHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mBinding.itemLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawers();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 6677);
            }
        });
        mBinding.incController.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawers();
                gotoSearch();
            }
        });

        mBinding.myBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawers();
                gotoMyBill();
            }
        });

        mBinding.itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoChangePass();
            }
        });

        mBinding.itemLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawers();
                new AlertDialog.Builder(MainActivity.this).setMessage(R.string.about_log_out)
                        .setTitle(R.string.dang_xuat)
                        .setPositiveButton(R.string.yes, (arg0, arg1) -> {
                            removeData();
                            Toast.makeText(MainActivity.this, "Logout success", Toast.LENGTH_SHORT).show();
                            mBinding.setIsLogin(false);
                        })
                        .setNegativeButton(R.string.no, (arg0, arg1) -> {

                        })
                        .show();
            }
        });

        mBinding.incController.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.drawerLayout.closeDrawers();
                gotoCart();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult: " + requestCode + "--" + resultCode);
        if (resultCode == 6688) {
            mBinding.setIsLogin(true);
        }
    }

    private void addHome() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
    }

    void setUpSlider() {
        adapter = new SliderAdapterExample(this);
        mBinding.imageSlider.setSliderAdapter(adapter);
        mBinding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mBinding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mBinding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        mBinding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        mBinding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        mBinding.imageSlider.setScrollTimeInSec(3);
        mBinding.imageSlider.setAutoCycle(true);
        mBinding.imageSlider.startAutoCycle();
    }

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
//        for (int i = 0; i < 5; i++) {
//            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);
//            if (i % 2 == 0) {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//            } else {
//                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
//            }
//            sliderItemList.add(sliderItem);
//        }
        sliderItemList.add(new SliderItem("Một số hình ảnh về cửa hàng", "https://cms.luatvietnam.vn/uploaded/Images/Original/2020/03/27/co-so-kinh-doanh-duoc-mo-cua_2703224203.jpg"));
        sliderItemList.add(new SliderItem("Một số hình ảnh về cửa hàng", "https://sobanhang.com/wp-content/uploads/2021/05/tap-hoa.jpg"));
        sliderItemList.add(new SliderItem("Một số hình ảnh về cửa hàng", "https://cdn-www.vinid.net/2020/08/5388aae8-shutterstock_515335339-1.jpg"));
        adapter.renewItems(sliderItemList);
    }

    public void refreshData() {
        mViewModel.getListCategory();
    }

    public void gotoSearch() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new SearchFragment(), "search").commit();
    }

    public void gotoCart() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new CartFragment(), "cart").commit();
    }

    public void gotoMyBill() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new MyBillFragment(), "billwaiting").commit();
    }

    public void gotoChangePass() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.parent_content, new ChangePassFragment(), "changepass").commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void removeData() {
        mSharedPreferences.edit().remove("tokenID").apply();
    }
}