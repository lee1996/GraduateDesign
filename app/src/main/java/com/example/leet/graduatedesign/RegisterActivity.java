package com.example.leet.graduatedesign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.User;
import Entity.UserDao;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;
import okhttputil.CallBackUtil;
import okhttputil.OkhttpUtil;

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
                    if(registerpwd.length()==0||registeruser.length()==0||registerpwdcon.length()==0){
                        Toast.makeText(RegisterActivity.this,"请完善信息",Toast.LENGTH_SHORT).show();
                    }else if(i==list.size()){
                        User user=new User(registeruser.getText().toString(),registerpwd.getText().toString());
                        userDao.insert(user);
                        SharedPreferences sharedPreferences=RegisterActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("isLogin",true);
                        editor.putString("username",registeruser.getText().toString());
                        editor.commit();
                        //测试okhttp
                        String url="http://118.89.160.240/GraduateDesign/login.action";
                        HashMap<String, String> paramsMap = new HashMap<>();
                        paramsMap.put("username",registeruser.getText().toString());
                        paramsMap.put("password",registerpwd.getText().toString());
                        OkhttpUtil.okHttpPost(url,paramsMap, new CallBackUtil() {
                            @Override
                            public Object onParseResponse(Call call, Response response) throws IOException {
                                Log.i("onparseresponse",response.toString());
                                return null;
                            }

                            @Override
                            public void onFailure(Call call, Exception e) {

                            }

                            @Override
                            public void onResponse(Object response) {
                                String str=response.toString();
                                Log.i("response test",str);

                            }
                        });

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(RegisterActivity.this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }
}
