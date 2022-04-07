package org.sonnnph12414.appduantotnghiep.activity;

import static org.sonnnph12414.appduantotnghiep.api.APIClient.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.api.APIClientlpm;
import org.sonnnph12414.appduantotnghiep.api.ConstUpdate;
import org.sonnnph12414.appduantotnghiep.fragment.HomeFragment;
import org.sonnnph12414.appduantotnghiep.model.EmailReponse;
import org.sonnnph12414.appduantotnghiep.model.RequestUpdate;
import org.sonnnph12414.appduantotnghiep.model.ResponseUpdate;
import org.sonnnph12414.appduantotnghiep.model.TokenResponse;
import org.sonnnph12414.appduantotnghiep.model.User;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForGetPassActivity extends AppCompatActivity {

    EditText edt_Email;
    Button btn_QuenMK;
    TextView tv_BackDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get_pass);
        edt_Email = findViewById(R.id.edt_Email);
        btn_QuenMK = findViewById(R.id.btn_quenMK);
        btn_QuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validater(edt_Email.getText().toString())) {
                    QuenMK();
                }
            }
        });
    }

    private boolean validater(String s) {

        String username = edt_Email.getText().toString().trim();

        if (username.equals("")) {
//            Toast.makeText(this, "Hãy nhập tài khoản !", Toast.LENGTH_SHORT).show();
            edt_Email.setError("Hãy nhập tài khoản !");
            edt_Email.requestFocus();
            return false;
        } else if (username.length() < 6 || username.length() > 30) {
//            Toast.makeText(this, "Tài khoản phải từ 6 - 30 kí tự !", Toast.LENGTH_SHORT).show();
            edt_Email.setError("Tài khoản phải từ 6 - 30 kí tự !");
            edt_Email.requestFocus();
            return false;
        }
        return true;
    }

    private void QuenMK() {
        try {
            final String username = edt_Email.getText().toString();

            APIClientlpm apiClientlpm = APIClient.LoginAPI().create(APIClientlpm.class);
            Call<ResponseBody> srvQuenMK = apiClientlpm.QuenMK(username);
            srvQuenMK.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        //                            String reponseJSON = response.body().string();
//                            Gson objGson = new Gson();
//                            EmailReponse emailReponse = objGson.fromJson(reponseJSON, EmailReponse.class);
//                            Toast.makeText(getApplicationContext(), emailReponse.getEmail(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Mật khẩu mới đã được gửi tới Email của bạn!", Toast.LENGTH_SHORT).show();
                        Intent BackToDN = new Intent(ForGetPassActivity.this, HomeActivity.class);
                        startActivity(BackToDN);
                    } else {
                        Toast.makeText(getApplicationContext(), "EmaForgetPassword đăng nhập, Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Lỗi hệ thống: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Lỗi hệ thống, Hãy chắc chắc bạn đã kết nối Internet!", Toast.LENGTH_SHORT).show();
        }
    }

    public void BackToDN(View view) {
        Intent BackToDN = new Intent(ForGetPassActivity.this, HomeActivity.class);
        startActivity(BackToDN);
    }
}