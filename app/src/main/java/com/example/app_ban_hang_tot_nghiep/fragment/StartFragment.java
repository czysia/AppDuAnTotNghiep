package com.example.app_ban_hang_tot_nghiep.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_ban_hang_tot_nghiep.MainActivity;
import com.example.app_ban_hang_tot_nghiep.R;
import com.example.app_ban_hang_tot_nghiep.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {

    private FragmentStartBinding mStartBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mStartBinding = FragmentStartBinding.inflate(inflater, container, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoMain();
            }
        }, 3000);
        // Inflate the layout for this fragment
        return mStartBinding.getRoot();
    }

    private void gotoMain() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}