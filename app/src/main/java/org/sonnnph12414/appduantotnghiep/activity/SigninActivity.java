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

import java.util.regex.Pattern;

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

    }

    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z][a-zA-Z\\-]{1,25}" +
                    ")+"
    );

    public void DKTaiKhoan(View view) {
        String strEmail = edt_Email.getText().toString().trim();
        String strPassword = edt_Password.getText().toString().trim();
        String strRepass = edt_RePass.getText().toString().trim();
        if (strEmail.isEmpty()) {
//            Toast.makeText(getApplicationContext(), " Hãy nhập Email/Username", Toast.LENGTH_SHORT).show();
            edt_Email.setError("Hãy nhập Email/Username!");
            edt_Email.requestFocus();
        } else if (!EMAIL_ADDRESS.matcher(strEmail).matches()) {
//            Toast.makeText(getApplicationContext(), "Hãy nhập đúng định dạng email", Toast.LENGTH_SHORT).show();
            edt_Email.setError("Hãy nhập đúng định dạng mail!");
            edt_Email.requestFocus();
        } else if (strPassword.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Hãy nhập mật khẩu !");
            edt_Password.requestFocus();
        } else if (strPassword.length() < 6) {
//            Toast.makeText(getApplicationContext(), "Mật khẩu không được dưới 6 ký tự", Toast.LENGTH_SHORT).show();
            edt_Password.setError("Mật khẩu không dưới 6 ký tự!");
            edt_Password.requestFocus();
        } else if (strRepass.isEmpty()) {
//            Toast.makeText(getApplicationContext(), "Hãy nhập mật khẩu", Toast.LENGTH_SHORT).show();
            edt_RePass.setError("Hãy nhập lại mật khẩu !");
            edt_RePass.requestFocus();
        } else if (strRepass.length() < 6) {
//            Toast.makeText(getApplicationContext(), "Mật khẩu không được dưới 6 ký tự", Toast.LENGTH_SHORT).show();
            edt_RePass.setError("Mật khẩu nhập lại không dưới 6 ký tự!");
            edt_RePass.requestFocus();
        } else if (strPassword.equals(strRepass)) {
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
                        //get data
                        ResponseUpdate responseUpdate = response.body();
                        Toast.makeText(getApplicationContext(), responseUpdate.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent backLogin = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(backLogin);
                    }

                    @Override
                    public void onFailure(Call<ResponseUpdate> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Mật khẩu nhập lại chưa khớp", Toast.LENGTH_SHORT).show();
        }
    }
}