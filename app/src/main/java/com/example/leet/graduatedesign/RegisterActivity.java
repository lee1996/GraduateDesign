package com.example.leet.graduatedesign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;

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
        final UserDao userDao= MyApplication.getInstances().getDaoSession().getUserDao();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(registerpwd.getText().toString().equals(registerpwdcon.getText().toString()))){
                    Toast.makeText(RegisterActivity.this,"请再次确认密码是否正确",Toast.LENGTH_SHORT).show();
                }else {
                    List<User> list=userDao.loadAll();
                    int i;
                    for(i=0;i<list.size();i++){
                        if(list.get(i).getUsername().equals(registeruser.getText().toString())){
                            break;
                        }
                    }
                    if(i==list.size()){
                        User user=new User(registeruser.getText().toString(),registerpwd.getText().toString());
                        userDao.insert(user);
                        SharedPreferences sharedPreferences=RegisterActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("isLogin",true);
                        editor.putString("username",registeruser.getText().toString());
                        editor.commit();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.putExtra("username",registeruser.getText().toString());
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this,"用户已存在",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
