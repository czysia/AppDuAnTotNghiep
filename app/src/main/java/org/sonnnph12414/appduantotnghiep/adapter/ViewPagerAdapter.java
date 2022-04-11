package org.sonnnph12414.appduantotnghiep.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.app_ban_hang_tot_nghiep.databinding.ItemViewPagerBinding;

import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    List<String> images;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapter(Context context, List<String> listImage) {
        this.context = context;
        this.images = listImage;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        ItemViewPagerBinding binding = ItemViewPagerBinding.inflate(mLayoutInflater, container, false);
//        View itemView = mLayoutInflater.inflate(R.layout.item_view_pager, container, false);

//        // referencing the image view from the item.xml file
//        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
//
//        // setting the image in the imageView
//        imageView.setImageResource(images[position]);
        binding.setUrlImage(images.get(position));

        // Adding the View
        Objects.requireNonNull(container).addView(binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
