package com.example.leet.graduatedesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.dd.CircularProgressButton;

import Base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-3-17.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.registeruser)
    EditText registeruser;
    @BindView(R.id.registerpwd)
    EditText registerpwd;
    @BindView(R.id.registerpwdcon)
    EditText registerpwdcon;
    @BindView(R.id.register)
    CircularProgressButton register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
