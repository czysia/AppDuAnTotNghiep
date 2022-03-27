package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.sonnnph12414.appduantotnghiep.MainActivity;
import org.sonnnph12414.appduantotnghiep.R;

public class LoginActivity extends AppCompatActivity {

    EditText edt_Username, edt_Password;
    Button btn_Login;
    TextView tv_ForGetPass, tv_Signin;
    //tạo biến để lưu data
    public static final String SHARED_PREFS = "SHAREDPREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Username = findViewById(R.id.edt_Username);
        edt_Password = findViewById(R.id.edt_Password);
        btn_Login = findViewById(R.id.btn_Login);
        tv_ForGetPass = findViewById(R.id.tv_forGetPass);
        tv_Signin = findViewById(R.id.tv_Signin);

    }

    private boolean validater(String s, String toString) {

        String username = edt_Username.getText().toString().trim();
        String password = edt_Password.getText().toString().trim();

        if (username.equals("")) {
//            Toast.makeText(this, "Hãy nhập tài khoản !", Toast.LENGTH_SHORT).show();
            edt_Username.setError("Hãy nhập tài khoản !");
            edt_Username.requestFocus();
            return false;
        } else if (username.length() < 6 || username.length() > 30) {
//            Toast.makeText(this, "Tài khoản phải từ 6 - 30 kí tự !", Toast.LENGTH_SHORT).show();
            edt_Username.setError("Tài khoản phải từ 6 - 30 kí tự !");
            edt_Username.requestFocus();
            return false;

        } else if (password.equals("")) {
//            Toast.makeText(this, "Hãy nhập mật khẩu !", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Hãy nhập mật khẩu !");
            edt_Password.requestFocus();
            return false;

        } else if (password.length() < 6 || password.length() > 10) {
//            Toast.makeText(this, "Mật khẩu phải từ 6 - 10 kí tự !", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Mật khẩu phải từ 6 - 10 kí tự !");
            edt_Password.requestFocus();
            return false;
        }
        return true;
    }

    //save data
    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", edt_Username.getText().toString());
        editor.apply();

//        editor.commit();
        String userName = sharedPreferences.getString("USERNAME", "not found");
        Log.e("user đăng nhập là ", ":" + userName);
        Toast.makeText(getApplicationContext(), "user đăng nhập  là " + userName, Toast.LENGTH_SHORT).show();
    }

    public void Login(View view) {
        if (validater(edt_Username.getText().toString(), edt_Password.getText().toString())) {
            saveData();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void ForGetPass(View view) {
        Intent ForGetPass = new Intent(LoginActivity.this, ForGetPassActivity.class);
        startActivity(ForGetPass);
    }

    public void Signin(View view) {
        Intent Signin = new Intent(LoginActivity.this, SigninActivity.class);
        startActivity(Signin);
    }
}