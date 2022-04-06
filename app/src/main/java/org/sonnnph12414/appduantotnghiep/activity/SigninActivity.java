package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.api.APIClientlpm;
import org.sonnnph12414.appduantotnghiep.api.ConstUpdate;
import org.sonnnph12414.appduantotnghiep.model.RequestUpdate;
import org.sonnnph12414.appduantotnghiep.model.ResponseUpdate;
import org.sonnnph12414.appduantotnghiep.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SigninActivity extends AppCompatActivity {

    EditText edt_Email, edt_Password, edt_RePass;
    Button btn_Signin;
    TextView tv_BackLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edt_Email = findViewById(R.id.edt_User_Signin);
        edt_Password = findViewById(R.id.edt_Pass_Signin);
        edt_RePass = findViewById(R.id.edt_RePassword);
        tv_BackLogin = findViewById(R.id.tv_backLogin);
        btn_Signin = findViewById(R.id.btn_Signin);
        tv_BackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_Login = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(back_Login);
            }
        });

        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validater(edt_Email.getText().toString(), edt_Password.getText().toString(), edt_RePass.getText().toString())) {
                    DKTaiKhoan();
                }
            }
        });
    }

    private boolean validater(String s, String toString, String String) {

        String strEmail = edt_Email.getText().toString().trim();
        String strPassword = edt_Password.getText().toString().trim();
        String strRepass = edt_RePass.getText().toString().trim();

        if (strEmail.equals("")) {
//            Toast.makeText(this, "Hãy nhập tài khoản !", Toast.LENGTH_SHORT).show();
            edt_Email.setError("Hãy nhập tài khoản !");
            edt_Email.requestFocus();
            return false;
        } else if (strEmail.length() < 6 || strEmail.length() > 30) {
//            Toast.makeText(this, "Tài khoản phải từ 6 - 30 kí tự !", Toast.LENGTH_SHORT).show();
            edt_Email.setError("Tài khoản phải từ 6 - 30 kí tự !");
            edt_Email.requestFocus();
            return false;

        } else if (strPassword.equals("")) {
//            Toast.makeText(this, "Hãy nhập mật khẩu !", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Hãy nhập mật khẩu !");
            edt_Password.requestFocus();
            return false;

        } else if (strPassword.length() < 6 || strPassword.length() > 10) {
//            Toast.makeText(this, "Mật khẩu phải từ 6 - 10 kí tự !", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Mật khẩu phải từ 6 - 10 kí tự !");
            edt_Password.requestFocus();
            return false;
        } else if (strRepass.equals("")) {
//            Toast.makeText(this, "Hãy nhập mật khẩu !", Toast.LENGTH_SHORT).show();
            edt_RePass.setError("Hãy nhập lại mật khẩu !");
            edt_RePass.requestFocus();
            return false;

        } else if (strRepass.length() < 6 || strRepass.length() > 10) {
//            Toast.makeText(this, "Mật khẩu phải từ 6 - 10 kí tự !", Toast.LENGTH_SHORT).show();
            edt_RePass.setError("Mật khẩu phải từ 6 - 10 kí tự !");
            edt_RePass.requestFocus();
            return false;
        } else if (strPassword!=strRepass) {
//            Toast.makeText(this, "Mật khẩu phải từ 6 - 10 kí tự !", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Mật khẩu chưa khớp, kiểm tra lại!");
            edt_RePass.setError("Mật khẩu chưa khớp, kiểm tra lại!");
            edt_Password.requestFocus();
            return false;
        }
        return true;
    }

    private void DKTaiKhoan() {
        try {
            User user = new User();
            user.setEmail(edt_Email.getText().toString().trim());
            user.setPassword(edt_Password.getText().toString().trim());
            user.setRepassword(edt_RePass.getText().toString().trim());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ConstUpdate.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            //2. Dua du lieu can update vao request
            RequestUpdate requestUpdate = new RequestUpdate();
            requestUpdate.setUser(user);
            //3.goi ham interface
            APIClientlpm apiClientl = retrofit.create(APIClientlpm.class);
            Call<ResponseUpdate> call = apiClientl.dangky(user.getId(), user.getEmail(), user.getPassword(), user.getRepassword());
            //4.thuc thi
            call.enqueue(new Callback<ResponseUpdate>() {
                @Override
                public void onResponse(Call<ResponseUpdate> call, Response<ResponseUpdate> response) {
                    if (response.isSuccessful()) {
                        try {
                            //get data
                            ResponseUpdate responseUpdate = response.body();
                            Toast.makeText(getApplicationContext(), responseUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent backLogin = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(backLogin);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Email hoặc mật khẩu không đúng, Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Lỗi hệ thống: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Lỗi hệ thống, Hãy chắc chắc bạn đã kết nối Internet!", Toast.LENGTH_SHORT).show();

        }
    }
}