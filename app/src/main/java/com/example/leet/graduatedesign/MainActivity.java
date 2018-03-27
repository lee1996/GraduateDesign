package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import Entity.BloodPre;
import Entity.BloodPreDao;
import Entity.BloodType;
import Entity.BloodTypeDao;
import Entity.Height;
import Entity.HeightDao;
import Entity.LeftEye;
import Entity.LeftEyeDao;
import Entity.RightEye;
import Entity.RightEyeDao;
import Entity.UserDao;
import Entity.Weight;
import Entity.WeightDao;
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
    private final UserDao userDao=MyApplication.getInstances().getDaoSession().getUserDao();
    private final HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
    private final WeightDao weightDao=MyApplication.getInstances().getDaoSession().getWeightDao();
    private final LeftEyeDao leftEyeDao=MyApplication.getInstances().getDaoSession().getLeftEyeDao();
    private final RightEyeDao rightEyeDao=MyApplication.getInstances().getDaoSession().getRightEyeDao();
    private final BloodTypeDao bloodTypeDao=MyApplication.getInstances().getDaoSession().getBloodTypeDao();
    private final BloodPreDao bloodPreDao=MyApplication.getInstances().getDaoSession().getBloodPreDao();
    String username;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        ButterKnife.bind(this);
        username=getIntent().getStringExtra("username").toString();
        user.setText(username);
        final WarningDialog dialog= SmartisanDialog.createWarningDialog(this);
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
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        weight.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), WeightUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        leftEye.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), LeftEyeUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        rightEye.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), RightEyeUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        bloodType.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), BloodTypeUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        bloodPressure.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), BloodPreUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        detail_picture.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(),PictureActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        init(username);

    }
    public void init(String username){
        List<Height> listHeight=heightDao.queryBuilder().where(HeightDao.Properties.User.eq(username)).build().list();
        List<Weight> listWeight=weightDao.queryBuilder().where(WeightDao.Properties.User.eq(username)).build().list();
        List<LeftEye> listLeftEye=leftEyeDao.queryBuilder().where(LeftEyeDao.Properties.User.eq(username)).build().list();
        List<RightEye> listRightEye=rightEyeDao.queryBuilder().where(RightEyeDao.Properties.User.eq(username)).build().list();
        List<BloodType> listBloodType=bloodTypeDao.queryBuilder().where(BloodTypeDao.Properties.User.eq(username)).build().list();
        List<BloodPre> listBloodPre=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        Log.i("height "," "+listHeight);
        if(listHeight.size()!=0) {
            height.setRightText(listHeight.get(listHeight.size() - 1).getHeight()+"cm");
        }
        if(listWeight.size()!=0){
            weight.setRightText(listWeight.get(listWeight.size()-1).getWeight()+"kg");
        }
        if(listLeftEye.size()!=0){
            leftEye.setRightText(listLeftEye.get(listLeftEye.size()-1).getLefteye());
        }
        if(listRightEye.size()!=0){
            rightEye.setRightText(listRightEye.get(listRightEye.size()-1).getRighteye());
        }
        if(listBloodType.size()!=0){
            bloodType.setRightText(listBloodType.get(listBloodType.size()-1).getBloodtype());
        }
        if(listBloodPre.size()!=0){
            bloodPressure.setRightText(listBloodPre.get(listBloodPre.size()-1).getShousuo()+"/"+listBloodPre.get(listBloodPre.size()-1).getShuzhang());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        username=getIntent().getStringExtra("username");
        init(username);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

}
