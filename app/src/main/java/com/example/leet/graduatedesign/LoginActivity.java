package com.example.leet.graduatedesign;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
import Entity.User;
import Entity.UserDao;
import Entity.Weight;
import Entity.WeightDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 17-11-26.
 */

public class LoginActivity extends Activity {
    @BindView(R.id.loginuser)
    EditText loginuser;
    @BindView(R.id.loginpwd)
    EditText loginpwd;
    @BindView(R.id.login)
    CircularProgressButton login;
    private final HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
    private final WeightDao weightDao=MyApplication.getInstances().getDaoSession().getWeightDao();
    private final LeftEyeDao leftEyeDao=MyApplication.getInstances().getDaoSession().getLeftEyeDao();
    private final RightEyeDao rightEyeDao=MyApplication.getInstances().getDaoSession().getRightEyeDao();
    private final BloodTypeDao bloodTypeDao=MyApplication.getInstances().getDaoSession().getBloodTypeDao();
    private final BloodPreDao bloodPreDao=MyApplication.getInstances().getDaoSession().getBloodPreDao();
    private String lastheight;
    private String lastweight;
    private String lastleft;
    private String lastright;
    private String lastbt;
    private String lastbp;
    private String lastshuzhang;
    private String lastshousuo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        final UserDao userDao = MyApplication.getInstances().getDaoSession().getUserDao();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        Looper.prepare();
                        URL url1=null;
                        URL url2=null;
                        try {
                            url1 = new URL("http://118.89.160.240:8080/GraduateDesign/login.action?username="+loginuser.getText().toString()
                                    +"&password="+loginpwd.getText().toString());
                            url2=new URL("http://118.89.160.240:8080/GraduateDesign/getlast.action?username="+loginuser.getText().toString());

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        String result= null;
                        String result2=null;
                        try {
                            result = downloadUrl(url1);
                            result2=downloadUrl(url2);
                            JSONObject jsonObject=new JSONObject(result2);
                            lastheight=jsonObject.getString("height");
                            lastweight=jsonObject.getString("weight");
                            lastleft=jsonObject.getString("lefteye");
                            lastright=jsonObject.getString("righteye");
                            lastbt=jsonObject.getString("bloodtype");
                            lastshousuo=jsonObject.getString("shousuo");
                            lastshuzhang=jsonObject.getString("shuzhang");

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(result.equals("1")){
                            SharedPreferences sharedPreferences=LoginActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("isLogin",true);
                            editor.putString("username",loginuser.getText().toString());
                            editor.commit();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username",loginuser.getText().toString());
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"账号或者密码错误！",Toast.LENGTH_SHORT).show();
                        }
                        Log.i("login response","  "+result);
                        Looper.loop();
                    }
                }).start();
                /*
                java.util.Date writeTime = new java.util.Date();
                Height height = new Height(lastheight, getIntent().getStringExtra("username"), writeTime.getTime());
                heightDao.insert(height);
                Weight weight=new Weight(lastweight,getIntent().getStringExtra("username"), writeTime.getTime());
                weightDao.insert(weight);
                LeftEye leftEye=new LeftEye(lastleft,getIntent().getStringExtra("username"), writeTime.getTime());
                leftEyeDao.insert(leftEye);
                RightEye rightEye=new RightEye(lastright,getIntent().getStringExtra("username"), writeTime.getTime());
                rightEyeDao.insert(rightEye);
                BloodType bt=new BloodType(lastbt,getIntent().getStringExtra("username"), writeTime.getTime());
                bloodTypeDao.insert(bt);
                BloodPre bp=new BloodPre(lastshousuo,lastshuzhang,getIntent().getStringExtra("username"), writeTime.getTime());
                bloodPreDao.insert(bp);
                Log.i("insert result is ","height is "+lastheight);
                */

            }
        });
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent=new Intent(LoginActivity.this,WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private  String downloadUrl(URL url) throws IOException {
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
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
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
}
