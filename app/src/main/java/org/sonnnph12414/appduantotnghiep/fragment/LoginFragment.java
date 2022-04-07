package org.sonnnph12414.appduantotnghiep.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.activity.ForGetPassActivity;
import org.sonnnph12414.appduantotnghiep.activity.HomeActivity;
import org.sonnnph12414.appduantotnghiep.activity.LoginActivity;
import org.sonnnph12414.appduantotnghiep.activity.SigninActivity;
import org.sonnnph12414.appduantotnghiep.api.APIClient;
import org.sonnnph12414.appduantotnghiep.api.APIClientlpm;
import org.sonnnph12414.appduantotnghiep.model.TokenResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    EditText edt_Username, edt_Password;
    Button btn_Login;
    TextView tv_ForGetPass, tv_Signin;
    View view;
//    public static final String SHARED_PREFS = "SHAREDPREFS";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);
        anhXa();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validater(edt_Username.getText().toString(), edt_Password.getText().toString())) {
                    saveData();
                }
            }
        });
        return view;
    }

    private void anhXa(){
        edt_Username = view.findViewById(R.id.edt_Username);
        edt_Password = view.findViewById(R.id.edt_Password);
        btn_Login = view.findViewById(R.id.btn_Login);
        tv_ForGetPass = view.findViewById(R.id.tv_forGetPass);
        tv_Signin = view.findViewById(R.id.tv_Signin);

        tv_ForGetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ForGetPass = new Intent(getActivity(), ForGetPassActivity.class);
                startActivity(ForGetPass);
            }
        });
        tv_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Signin = new Intent(getActivity(), SigninActivity.class);
                startActivity(Signin);
            }
        });
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
    private void saveData() {
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("USERNAME", edt_Username.getText().toString());
//        editor.apply();
//
////        editor.commit();
//        String userName = sharedPreferences.getString("USERNAME", "not found");
//        Log.e("user đăng nhập là ", ":" + userName);
//        Toast.makeText(getActivity(), "user đăng nhập  là " + userName, Toast.LENGTH_SHORT).show();
        try {
            final String username = edt_Username.getText().toString();
            final String password = edt_Password.getText().toString();

            APIClientlpm apiClientlpm = APIClient.LoginAPI().create(APIClientlpm.class);
            Call<ResponseBody> srvLogin = apiClientlpm.getToken(username, password);
            srvLogin.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            String reponseJSON = response.body().string();
                            Gson objGson = new Gson();
                            TokenResponse tokenResponse = objGson.fromJson(reponseJSON, TokenResponse.class);
//                            Toast.makeText(getActivity(), tokenResponse.getToken(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                            Fragment fragment = new HomeFragment();
                            Bundle bundle = new Bundle();
                            fragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_fame, fragment, null)
                                    .addToBackStack(null)
                                    .commit();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng, Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Lỗi hệ thống: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Lỗi hệ thống, Hãy chắc chắc bạn đã kết nối Internet!", Toast.LENGTH_SHORT).show();
        }
    }
}