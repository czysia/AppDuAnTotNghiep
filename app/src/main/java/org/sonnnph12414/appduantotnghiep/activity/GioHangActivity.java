package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.sonnnph12414.appduantotnghiep.R;

public class GioHangActivity extends AppCompatActivity {
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

    private void initView() {
        giohangtrong = findViewById(R.id.txtgiohangtrong);
        toolbar =findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerviewgiohang);
        tongtien =findViewById(R.id.txttongtien);
        btnmuahang =findViewById(R.id.btnmuahang);
    }
}