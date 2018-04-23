package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.User;
import Entity.UserDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-3-17.
 */

public class WelcomeActivity extends Activity {
    private ImmersionBar mImmersionBar;
    @BindView(R.id.tologin)
    Button tologin;
    @BindView(R.id.toregist)
    Button toregist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        if(isLogin==true){
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            intent.putExtra("username",sharedPreferences.getString("username","username"));
            startActivity(intent);
            finish();
        }
        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=WelcomeActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("isLogin",false);
                editor.commit();
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        toregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=WelcomeActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("isLogin",false);
                editor.commit();
                Intent intent=new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //UserDao userDao= MyApplication.getInstances().getDaoSession().getUserDao();
        //List<User> list=userDao.loadAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }
}
