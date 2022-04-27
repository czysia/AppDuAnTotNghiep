package com.example.app_ban_hang_tot_nghiep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.app_ban_hang_tot_nghiep.databinding.ActivityInforDevBinding;
import com.example.app_ban_hang_tot_nghiep.utils.RealPathUtil;
import com.example.app_ban_hang_tot_nghiep.viewmodel.UserInfoViewModel;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class InforUserActivity extends AppCompatActivity {

    private ActivityInforDevBinding mBinding;
    private UserInfoViewModel mViewModel;
    private String token = "";
    private Uri urlInfo;
    private String urlImage;
    public SharedPreferences mSharedPreferences;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final int PICK_FROM_GALLERY = 1099;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_infor_dev);
        mSharedPreferences = this.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        setFullScreen();
        token = mSharedPreferences.getString("tokenID", "xxx");
        setUpViewModel();
        onClick();
    }

    private void setUpViewModel() {
        mViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);
        mViewModel.getUser(token);
        mViewModel.userInfor.observe(this, data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            mBinding.setImgUrl(data.getAvatar());
            mBinding.setEdtAdress(data.getAddress());
            mBinding.setEdtName(data.getName());
            mBinding.setEdtPhone(data.getPhone());
            urlImage = data.getAvatar();
        });
        mViewModel.isUpdateSuccess.observe(this, data -> {
            mBinding.spinKit.setVisibility(View.GONE);
            if (data) {
                Toast.makeText(InforUserActivity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(InforUserActivity.this, "Cập nhật thông tin không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFullScreen() {
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        this.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    private void onClick() {

        mBinding.imgEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (ActivityCompat.checkSelfPermission(InforUserActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(InforUserActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    } else {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mBinding.imgAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.edtDC.getText().toString().trim().equals("") | mBinding.edtNameUser.getText().toString().trim().equals("") | mBinding.edtPhone.getText().toString().trim().equals("")) {
                    Toast.makeText(InforUserActivity.this, "Không để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBinding.spinKit.setVisibility(View.VISIBLE);

                Glide.with(InforUserActivity.this)
                        .asBitmap()
                        .load(urlImage)
                        .into(new CustomTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                File file = bitmaptoFile(resource);
                                updateData(file);
                            }

                            @Override
                            public void onLoadCleared(@Nullable Drawable placeholder) {
                            }
                        });
            }
        });

        mBinding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PICK_FROM_GALLERY:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
                } else {
                    //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY) {
            if (data != null) {
                Uri uriImage = data.getData();
                urlInfo = uriImage;
                urlImage = data.getData().toString();
                mBinding.imgAvatar.setImageURI(uriImage);
            }
        }
    }


    private void updateData(File file) {
        RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"), mBinding.edtNameUser.getText().toString().trim());
        RequestBody requestBodyAddress = RequestBody.create(MediaType.parse("multipart/form-data"), mBinding.edtDC.getText().toString().trim());
        RequestBody requestBodyPhone = RequestBody.create(MediaType.parse("multipart/form-data"), mBinding.edtPhone.getText().toString().trim());
        RequestBody requestBodyToken = RequestBody.create(MediaType.parse("multipart/form-data"), token);
//        String path = RealPathUtil.getRealPath(this, urlInfo);
//        File file = new File(path);

        RequestBody requestBodyAvatar = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multiPart = MultipartBody.Part.createFormData("avatar", file.getName(), requestBodyAvatar);
        mViewModel.updateInfoUse(multiPart, requestBodyAddress, requestBodyName, requestBodyPhone, requestBodyToken);
    }

    private File bitmaptoFile(Bitmap bitmap) {
//        val wrapper = ContextWrapper(applicationContext)

        // Initialize a new file instance to save bitmap object
//        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
//        file = File(file, "${UUID.randomUUID()}.jpg");

        ContextWrapper wrapper1 = new ContextWrapper(getApplicationContext());

        File f = wrapper1.getDir("Images", Context.MODE_PRIVATE);
        f = new File(f, UUID.randomUUID() + ".jpg");

        //Convert bitmap to byte array
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (Exception ex) {

        }
        return f;
    }
}