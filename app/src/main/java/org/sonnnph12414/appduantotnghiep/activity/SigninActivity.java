package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.sonnnph12414.appduantotnghiep.R;

public class SigninActivity extends AppCompatActivity {

    EditText edt_Username, edt_Password, edt_RePass;
    Button btn_Signin;
    TextView tv_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edt_Username = findViewById(R.id.edt_User_Signin);
        edt_Password = findViewById(R.id.edt_Pass_Signin);
        edt_RePass = findViewById(R.id.edt_RePassword);
        btn_Signin = findViewById(R.id.btn_Signin);
        tv_Login = findViewById(R.id.tv_Login);
    }

    public void DKTaiKhoan(View view) {
        Intent DKTaiKhoan = new Intent(SigninActivity.this, LoginActivity.class);
        startActivity(DKTaiKhoan);
    }

    public void TroLaiDN(View view) {
        Intent TroLaiDN = new Intent(SigninActivity.this, LoginActivity.class);
        startActivity(TroLaiDN);
    }
}