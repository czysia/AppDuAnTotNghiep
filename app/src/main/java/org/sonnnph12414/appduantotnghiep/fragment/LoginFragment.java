package org.sonnnph12414.appduantotnghiep.fragment;

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

import org.sonnnph12414.appduantotnghiep.R;
import org.sonnnph12414.appduantotnghiep.activity.ForGetPassActivity;
import org.sonnnph12414.appduantotnghiep.activity.HomeActivity;
import org.sonnnph12414.appduantotnghiep.activity.LoginActivity;
import org.sonnnph12414.appduantotnghiep.activity.SigninActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    EditText edt_Username, edt_Password;
    Button btn_Login;
    TextView tv_ForGetPass, tv_Signin;
    View view;
    public static final String SHARED_PREFS = "SHAREDPREFS";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_login, container, false);
        anhXa();
        return view;
    }

    private void anhXa(){
        edt_Username = view.findViewById(R.id.edt_Username);
        edt_Password = view.findViewById(R.id.edt_Password);
        btn_Login = view.findViewById(R.id.btn_Login);
        tv_ForGetPass = view.findViewById(R.id.tv_forGetPass);
        tv_Signin = view.findViewById(R.id.tv_Signin);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validater(edt_Username.getText().toString(), edt_Password.getText().toString())) {
                    saveData();
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
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
    public void saveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USERNAME", edt_Username.getText().toString());
        editor.apply();

//        editor.commit();
        String userName = sharedPreferences.getString("USERNAME", "not found");
        Log.e("user đăng nhập là ", ":" + userName);
        Toast.makeText(getActivity(), "user đăng nhập  là " + userName, Toast.LENGTH_SHORT).show();
    }
}