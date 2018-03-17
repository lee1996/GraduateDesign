package com.example.leet.graduatedesign;

import android.app.Activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.util.ArrayList;
import java.util.List;

import Base.BaseActivity;

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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
