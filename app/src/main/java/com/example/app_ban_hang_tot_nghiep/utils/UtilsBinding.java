package com.example.app_ban_hang_tot_nghiep.utils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class UtilsBinding {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view).load(url).into(view);
    }

    @BindingAdapter(value = {"counter", "pricesvalue"}, requireAll = false)
    public static void loadImage(TextView view, int count, int prices) {
        int total = count * prices;
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance(localeVN));
        view.setText(format.format(total));
    }
}
