package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_ban_hang_tot_nghiep.LoginActivity;
import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.adapter.CartAdapter;
import com.example.app_ban_hang_tot_nghiep.adapter.SliderAdapterExample;
import com.example.app_ban_hang_tot_nghiep.adapter.ViewPagerAdapter;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentDetailProductBinding;
import com.example.app_ban_hang_tot_nghiep.model.SliderItem;
import com.example.app_ban_hang_tot_nghiep.utils.Utils;
import com.example.app_ban_hang_tot_nghiep.viewmodel.DetailViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class DetailProductFragment extends Fragment {
    public FragmentDetailProductBinding mBinding;
    public SharedPreferences mSharedPreferences;
    public DetailViewModel mViewModel;
    private SliderAdapterExample adapter;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final String ARG_PARAM1 = "id";
    private static final String ARG_PARAM6 = "name";
    private static final String ARG_PARAM2 = "prices";
    private static final String ARG_PARAM3 = "des";
    private static final String ARG_PARAM5 = "listImage";
    private static final String ARG_PARAM7 = "quality";

    // TODO: Rename and change types of parameters
    private int count = 1;
    private String token = "";
    private String id;
    private String name;
    private Integer prices, quality;
    private String des;
    private List<String> mListImage;


    // TODO: Rename and change types and number of parameters
    public static DetailProductFragment newInstance(String id, String name, int prices, String des, int quality, ArrayList<String> listString) {
        DetailProductFragment fragment = new DetailProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, id);
        args.putInt(ARG_PARAM2, prices);
        args.putString(ARG_PARAM3, des);
        args.putStringArrayList(ARG_PARAM5, listString);
        args.putString(ARG_PARAM6, name);
        args.putInt(ARG_PARAM7, quality);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ARG_PARAM1);
            prices = getArguments().getInt(ARG_PARAM2);
            des = getArguments().getString(ARG_PARAM3);
            mListImage = getArguments().getStringArrayList(ARG_PARAM5);
            name = getArguments().getString(ARG_PARAM6);
            quality = getArguments().getInt(ARG_PARAM7);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentDetailProductBinding.inflate(inflater, container, false);
        mSharedPreferences = requireContext().getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        mBinding.setName(name);
        mBinding.setDescription(des);
        mBinding.setPrices(new Utils().convertMoney(prices));
        mBinding.setPricesInt(prices);
        mBinding.setCountItem(1);
        mBinding.setQuality(quality);
        setUpSlider();
        onClick();
        setUpViewModel();
        renewItems();
//        int[] images = {R.drawable.anhtest, R.drawable.anhtest, R.drawable.anhtest, R.drawable.anhtest};
//        mAdapter = new ViewPagerAdapter(getContext(), mListImage);
//        mBinding.vpSlide.setAdapter(mAdapter);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void onClick() {
        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mBinding.imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count > quality) {
                    return;
                }
                count = count + 1;
                mBinding.setCountItem(count);
            }
        });
        mBinding.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 1) {
                    return;
                }
                count = count - 1;
                mBinding.setCountItem(count);
            }
        });

        mBinding.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSharedPreferences.getString("tokenID", "xxx").equals("xxx")) {
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivityForResult(intent, 6677);
                    return;
                }
                token = mSharedPreferences.getString("tokenID", "xxx");
                Log.d("TAG234", "onClick: " + id);
                mBinding.spinKit.setVisibility(View.VISIBLE);
                mViewModel.addProductToCart(id, token, count);
            }
        });
    }

    public void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mViewModel.listData.observe(getViewLifecycleOwner(), aBoolean -> {
            mBinding.spinKit.setVisibility(View.GONE);
            Log.d("TAG", "setUpViewModel: " + aBoolean);
            if (aBoolean) {
                requireActivity().onBackPressed();
                Toast.makeText(getContext(), "Thêm sản phẩm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Thêm sản phẩm vào giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setUpSlider() {
        adapter = new SliderAdapterExample(requireContext());
        mBinding.vpSlide.setSliderAdapter(adapter);
        mBinding.vpSlide.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mBinding.vpSlide.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mBinding.vpSlide.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        mBinding.vpSlide.setIndicatorSelectedColor(Color.WHITE);
        mBinding.vpSlide.setIndicatorUnselectedColor(Color.GRAY);
        mBinding.vpSlide.setScrollTimeInSec(3);
        mBinding.vpSlide.setAutoCycle(true);
        mBinding.vpSlide.startAutoCycle();
    }

    public void renewItems() {
        List<SliderItem> sliderItemList = new ArrayList<>();

        for (int i = 0; i < mListImage.size(); i++) {
            sliderItemList.add(new SliderItem("", mListImage.get(i)));
        }
        adapter.renewItems(sliderItemList);
    }
}