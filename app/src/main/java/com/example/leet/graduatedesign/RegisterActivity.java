package com.example.leet.graduatedesign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Base.IsNetWork;
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
                      //  String url="http://118.89.160.240/GraduateDesign/login.action";
                        new Thread(new Runnable(){
                            @Override
                            public void run() {
                                URL url1= null;
                                try {
                                    url1 = new URL("http://118.89.160.240:8080/GraduateDesign/login.action?username=liyu&password=123456");
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                String result= null;
                                try {
                                    result = downloadUrl(url1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.i("response","  "+result);
                            }
                        }).start();


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

    private String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
                // For this use case, set HTTP method to GET.
                connection.setRequestMethod("GET");
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                connection.setDoInput(true);
                // Open communications link (network traffic occurs here).
                connection.connect();
            // Log.d("networkfragment","  just for test");
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(stream,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine())!=null)
            {
                builder.append(line);
            }

            br.close();
            isr.close();
            stream.close();
            result = builder.toString();
            Log.v("result in Fragment is",result);
                /*
                if (stream != null) {
                    // Converts Stream to String with max length of 500.
                    result = readStream(stream,);
                }
                */

        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onStart() {
        super.onStart();
        IsNetWork.checkNetwork(this);
    }
}
