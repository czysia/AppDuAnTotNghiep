package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.model.UpdateUser;
import org.sonnnph12414.appduantotnghiep.model.User;


public class SigninActivity extends AppCompatActivity {

    EditText edt_Username, edt_Password, edt_RePass;
    Button btn_Signin;
    TextView tv_Login;
    ProgressBar LoadingPB;
    String tvResult = "";


//    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edt_Username = findViewById(R.id.edt_User_Signin);
        edt_Password = findViewById(R.id.edt_Pass_Signin);
        edt_RePass = findViewById(R.id.edt_RePassword);
        LoadingPB = findViewById(R.id.idLoadingPB);
        btn_Signin = findViewById(R.id.btn_Signin);
        tv_Login = findViewById(R.id.tv_Login);
    }



    public void DKTaiKhoan(View view) {
        String strEmail = edt_RePass.getText().toString().trim();
        String strPassword = edt_RePass.getText().toString().trim();
        String strRepass = edt_RePass.getText().toString().trim();

        if (strEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
        } else if (strPassword.isEmpty()){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (strRepass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập lại Mật khẩu", Toast.LENGTH_SHORT).show();
        } else {
            if (strPassword.equals(strRepass)){
                UpdateUser u = new UpdateUser();
                User user = new User();
                user.setEmail(edt_Username.getText().toString().trim());
                user.setPassword(edt_Password.getText().toString().trim());
                user.setRepassword(edt_RePass.getText().toString().trim());
                u.updateUserFn(tvResult,user);
                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại chưa khớp", Toast.LENGTH_SHORT).show();
            }
        }
    }

//
    public void TroLaiDN(View view) {
        Intent TroLaiDN = new Intent(SigninActivity.this, LoginActivity.class);
        startActivity(TroLaiDN);
    }
}