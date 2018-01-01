package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;
import Base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;
import kotlin.collections.MapsKt;

import static cc.duduhuo.dialog.smartisan.SmartisanDialog.createWarningDialog;

/**
 * Created by leet on 17-11-28.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.person)
    ImageView person;
    @BindView(R.id.scan)
    ImageView scan;
    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        fab.attachToListView(listView);
        Map<String,String> maps1=new HashMap<>();
        maps1.put("name","liyu");
        maps1.put("number","123456");
        Map<String,String> maps2=new HashMap<>();
        maps2.put("name","tangting");
        maps2.put("number","123456");
        List<Map<String,String>> list=new ArrayList<>();
        list.add(maps1);
        list.add(maps2);
        MyAdapter myAdapter=new MyAdapter(this,list);
        listView.setAdapter(myAdapter);

        //增加
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.in,R.anim.activity_stay);
            }
        });

    }


}
