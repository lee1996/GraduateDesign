package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.gyf.barlibrary.ImmersionBar;

import Base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 17-12-5.
 */

public class CertainActivity extends Activity {
    @BindView(R.id.certain)
    Button certain;
    @BindView(R.id.cancel)
    Button cancel;
    private ImmersionBar mImmersionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.certainpage);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        ButterKnife.bind(this);
        certain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CertainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CertainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
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
