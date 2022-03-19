package org.sonnnph12414.appduantotnghiep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GioHang extends AppCompatActivity {
TextView giohangtrong,tongtien;
Toolbar toolbar;
RecyclerView recyclerView;
Button btnmuahang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        initView();
    }
    private  void initView(){
        giohangtrong = findViewById(R.id.txtgiahangtrong);
        tongtien = findViewById(R.id.txttongtien);
        recyclerView = findViewById(R.id.recycleviewgiohang);
        toolbar = findViewById(R.id.toolbar);
        btnmuahang = findViewById(R.id.btnmuahang);

    }
}