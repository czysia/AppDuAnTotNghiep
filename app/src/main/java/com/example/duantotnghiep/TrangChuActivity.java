package com.example.duantotnghiep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.duantotnghiep.Adapter.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class TrangChuActivity extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {R.drawable.slide1,
    R.drawable.slide2,
    R.drawable.slide3,
    R.drawable.slide4,
    R.drawable.slide5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        sliderView = findViewById(R.id.imageSlider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();
    }
}