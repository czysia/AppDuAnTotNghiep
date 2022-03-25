package com.example.duantotnghiep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DangKyActivity extends AppCompatActivity {
    Button btnSignUp;
    TextView txtSignIn;
    TextInputLayout inputEmail,inputPass, inputRePass;
    TextInputEditText edtEmail, edtPassword, edtRePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhxaDangKy();
        DangKy();
        ManDangNhap();
    }

    private void AnhxaDangKy(){
        btnSignUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtSignIn);
        inputEmail = findViewById(R.id.inputEmail);
        inputPass = findViewById(R.id.inputPass);
        inputRePass = findViewById(R.id.inputRePass);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);
    }

    private void DangKy(){
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(DangKyActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void ManDangNhap(){
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}