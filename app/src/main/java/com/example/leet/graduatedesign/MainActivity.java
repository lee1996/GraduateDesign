package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.MyAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import kotlin.collections.MapsKt;

/**
 * Created by leet on 17-11-28.
 */

public class MainActivity extends Activity {
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
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ScanActivity.class);
                startActivity(intent);
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

    }
}
