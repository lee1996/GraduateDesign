package com.example.leet.graduatedesign;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.util.ArrayList;
import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;

import Entity.User;
import Entity.UserDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 17-11-26.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.loginuser)
    EditText loginuser;
    @BindView(R.id.loginpwd)
    EditText loginpwd;
    @BindView(R.id.login)
    CircularProgressButton login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        final UserDao userDao = MyApplication.getInstances().getDaoSession().getUserDao();
//        TelephonyManager telephonyManager = (TelephonyManager) LoginActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        final String phonenum = telephonyManager.getLine1Number();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> list = userDao.loadAll();
                int i = 0;
                for (User user : list) {
                    if (loginuser.getText().toString().equals(user.getUsername()) && loginpwd.getText().toString().equals(user.getPassword())) {

                        //Log.i("phonenum","电话号码是"+phonenum);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username",loginuser.getText().toString());
                        startActivity(intent);
                        finish();
                    }else{
                        i++;
                    }
                }
                if(i==list.size()) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
