package org.sonnnph12414.appduantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.sonnnph12414.appduantotnghiep.R;

public class ForGetPassActivity extends AppCompatActivity {

    EditText edt_Email;
    Button btn_QuenMK;
    TextView tv_quenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get_pass);
        edt_Email = findViewById(R.id.edt_Email);
        btn_QuenMK = findViewById(R.id.btn_quenMK);
        tv_quenMK = findViewById(R.id.tv_backToDN);
    }

    public void QuenMK(View view) {
        Intent QuenMK = new Intent(ForGetPassActivity.this, LoginActivity.class);
        startActivity(QuenMK);
    }

    public void BackToDN(View view) {
        Intent BackToDN = new Intent(ForGetPassActivity.this, LoginActivity.class);
        startActivity(BackToDN);
    }
}