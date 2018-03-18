package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.leon.lib.settingview.LSettingItem;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;
import Application.MyApplication;
import Base.BaseActivity;
import Entity.HeightDao;
import Entity.UserDao;
import Update.BloodPreUpdateActivity;
import Update.BloodTypeUpdateActivity;
import Update.HeightUpdateActivity;
import Update.LeftEyeUpdateActivity;
import Update.RightEyeUpdateActivity;
import Update.WeightUpdateActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;


import static cc.duduhuo.dialog.smartisan.SmartisanDialog.createWarningDialog;

/**
 * Created by leet on 17-11-28.
 */

public class MainActivity extends Activity {
    @BindView(R.id.user)
    TextView user;
    @BindView(R.id.person)
    ImageView person;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.height)
    LSettingItem height;
    @BindView(R.id.weight)
    LSettingItem weight;
    @BindView(R.id.leftEye)
    LSettingItem leftEye;
    @BindView(R.id.rightEye)
    LSettingItem rightEye;
    @BindView(R.id.bloodType)
    LSettingItem bloodType;
    @BindView(R.id.detail_picture)
    LSettingItem detail_picture;
    @BindView(R.id.bloodPressure)
    LSettingItem bloodPressure;
    private ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        ButterKnife.bind(this);
        user.setText(getIntent().getStringExtra("username"));
        final WarningDialog dialog= SmartisanDialog.createWarningDialog(this);
        UserDao userDao=MyApplication.getInstances().getDaoSession().getUserDao();
        HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle("确定退出登录吗？").setConfirmText("退出登录").show();
                dialog.setOnConfirmListener(new WarningDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(MainActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ScanActivity.class);
                startActivity(intent);
//                Intent intent=new Intent(MainActivity.this,CertainActivity.class);
//                startActivity(intent);
            }
        });

        height.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), HeightUpdateActivity.class);
                startActivity(intent);
            }
        });
        weight.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), WeightUpdateActivity.class);
                startActivity(intent);
            }
        });
        leftEye.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), LeftEyeUpdateActivity.class);
                startActivity(intent);
            }
        });
        rightEye.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), RightEyeUpdateActivity.class);
                startActivity(intent);
            }
        });
        bloodType.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), BloodTypeUpdateActivity.class);
                startActivity(intent);
            }
        });
        bloodPressure.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), BloodPreUpdateActivity.class);
                startActivity(intent);
            }
        });
        detail_picture.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(),PictureActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

}
