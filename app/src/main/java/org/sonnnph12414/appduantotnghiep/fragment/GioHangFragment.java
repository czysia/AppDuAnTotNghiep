package org.sonnnph12414.appduantotnghiep.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.sonnnph12414.appduantotnghiep.R;

public class GioHangFragment extends Fragment {
    TextView giohangtrong,tongtien;
    Toolbar toolbar;
    RecyclerView recyclerView;
    Button btnmuahang;

    public GioHangFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gio_hang,container,false);

        giohangtrong = view.findViewById(R.id.txtgiohangtrong);
        toolbar = view.findViewById(R.id.toolbar);
        recyclerView = view.findViewById(R.id.recyclerviewgiohang);
        tongtien = view.findViewById(R.id.txttongtien);
        btnmuahang = view.findViewById(R.id.btnmuahang);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
