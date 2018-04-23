package com.example.leet.graduatedesign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.leon.lib.settingview.LSettingItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.Age;
import Entity.AgeDao;
import Entity.BloodPre;
import Entity.BloodPreDao;
import Entity.BloodType;
import Entity.BloodTypeDao;
import Entity.Gender;
import Entity.GenderDao;
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
import Update.AgeUpdateActivity;
import Update.BloodTypeUpdateActivity;
import Update.HeightUpdateActivity;
import Update.LeftEyeUpdateActivity;
import Update.RightEyeUpdateActivity;
import Update.WeightUpdateActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cc.duduhuo.dialog.smartisan.OptionListDialog;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;
import cc.duduhuo.dialog.smartisan.TwoOptionsDialog;
import cc.duduhuo.dialog.smartisan.WarningDialog;
import cc.duduhuo.dialog.smartisan.listener.OnOptionItemSelectListener;

/**
 * Created by leet on 18-4-18.
 */

public class BaseInfoActivity extends BaseActivity {

    @BindView(R.id.gender)
    LSettingItem gender;
    @BindView(R.id.age)
    LSettingItem age;
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
    @BindView(R.id.basetomain)
    ImageView basetomain;

    private ImmersionBar mImmersionBar;

    private final UserDao userDao= MyApplication.getInstances().getDaoSession().getUserDao();
    private final GenderDao genderDao=MyApplication.getInstances().getDaoSession().getGenderDao();
    private final AgeDao ageDao=MyApplication.getInstances().getDaoSession().getAgeDao();
    private final HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
    private final WeightDao weightDao=MyApplication.getInstances().getDaoSession().getWeightDao();
    private final LeftEyeDao leftEyeDao=MyApplication.getInstances().getDaoSession().getLeftEyeDao();
    private final RightEyeDao rightEyeDao=MyApplication.getInstances().getDaoSession().getRightEyeDao();
    private final BloodTypeDao bloodTypeDao=MyApplication.getInstances().getDaoSession().getBloodTypeDao();
    private final BloodPreDao bloodPreDao=MyApplication.getInstances().getDaoSession().getBloodPreDao();
    String username;
    String password;
    private long firstTime = 0;
    private String lastheight;
    private String lastweight;
    private String lastleft;
    private String lastright;
    private String lastbt;
    private String lastbp;
    private String lastshuzhang;
    private String lastshousuo;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                height.setRightText(lastheight);
                weight.setRightText(lastweight);
                leftEye.setRightText(lastleft);
                rightEye.setRightText(lastright);
                bloodType.setRightText(lastbt);

            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseinfo);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        username=sharedPreferences.getString("username","username");
        password=sharedPreferences.getString("password","password");
        final TwoOptionsDialog dialog = SmartisanDialog.createTwoOptionsDialog(this);
        basetomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gender.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                dialog.setTitle("请选择您的性别")
                        .setOp1Text("男")
                        .setOp2Text("女")
                        .show();
                dialog.setOnSelectListener(new TwoOptionsDialog.OnSelectListener() {
                    @Override
                    public void onOp1() {
                        Gender gender1=new Gender(username,"男");
                        genderDao.insert(gender1);
                        gender.setRightText("男");
                        new Thread(new Runnable(){
                            @Override
                            public void run() {
                                URL url1= null;
                                try {
                                    url1 = new URL("http://118.89.160.240:8080/GraduateDesign/gender.action?username="+username+"&gender="+"男");
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                String result= null;
                                try {
                                    result = downloadUrl(url1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.i("age response","  "+result);
                            }
                        }).start();
                        dialog.dismiss();
                    }

                    @Override
                    public void onOp2() {
                        Gender gender1=new Gender(username,"女");
                        genderDao.insert(gender1);
                        gender.setRightText("女");
                        new Thread(new Runnable(){
                            @Override
                            public void run() {
                                URL url1= null;
                                try {
                                    url1 = new URL("http://118.89.160.240:8080/GraduateDesign/gender.action?username="+username+"&gender="+"女");
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                String result= null;
                                try {
                                    result = downloadUrl(url1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.i("age response","  "+result);
                            }
                        }).start();
                        dialog.dismiss();
                    }
                });
            }
        });
        age.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), AgeUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
        height.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), HeightUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
        weight.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), WeightUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
        leftEye.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), LeftEyeUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
        rightEye.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Intent intent=new Intent(getApplicationContext(), RightEyeUpdateActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

        bloodType.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
//                Intent intent=new Intent(getApplicationContext(), BloodTypeUpdateActivity.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
//                finish();
                List<String> list=new ArrayList<>();
                list.add("A");
                list.add("B");
                list.add("AB");
                list.add("O");
                list.add("Rh");
                final OptionListDialog dialog1 = SmartisanDialog.createOptionListDialog(BaseInfoActivity.this);
                dialog1.setTitle("请选择您的血型")
                        .setOptionList(list)
                        .setItemGravity(Gravity.CENTER) // Item是居左、居中还是居右
                        .setLastColor(0xFF40B64A)   // 上次选择的选项显示的颜色，用于区分
                        .show();
                dialog1.setOnOptionItemSelectListener(new OnOptionItemSelectListener() {
                    @Override
                    public void onSelect(final int position, final CharSequence option) {

                        //Toast.makeText(BaseInfoActivity.this, "position = " + position + ", option = " + option, Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable(){
                            @Override
                            public void run() {
                                URL url1= null;
                                try {
                                    url1 = new URL("http://118.89.160.240:8080/GraduateDesign/bloodtype.action?username="+username+"&bt="+option);
                                } catch (MalformedURLException e) {
                                    e.printStackTrace();
                                }
                                String result= null;
                                try {
                                    result = downloadUrl(url1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.i("bloodtype response","  "+result);
                            }
                        }).start();
                        bloodType.setRightText(option.toString());
                        dialog1.dismiss();
                    }
                });
            }
        });
        init(username);
    }

    public void init(String username){
        List<Gender> listGender=genderDao.queryBuilder().where(GenderDao.Properties.Username.eq(username)).build().list();
        List<Age> listAge=ageDao.queryBuilder().where(AgeDao.Properties.Username.eq(username)).build().list();
        List<Height> listHeight=heightDao.queryBuilder().where(HeightDao.Properties.User.eq(username)).build().list();
        List<Weight> listWeight=weightDao.queryBuilder().where(WeightDao.Properties.User.eq(username)).build().list();
        List<LeftEye> listLeftEye=leftEyeDao.queryBuilder().where(LeftEyeDao.Properties.User.eq(username)).build().list();
        List<RightEye> listRightEye=rightEyeDao.queryBuilder().where(RightEyeDao.Properties.User.eq(username)).build().list();
        List<BloodType> listBloodType=bloodTypeDao.queryBuilder().where(BloodTypeDao.Properties.User.eq(username)).build().list();
        List<BloodPre> listBloodPre=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        //Log.i("height "," "+listHeight);
        if(listGender.size()!=0){
            gender.setRightText(listGender.get(listGender.size()-1).getGender());
        }
        if(listAge.size()!=0){
            age.setRightText(String.valueOf(listAge.get(listAge.size()-1).getAge()));
        }
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

    }

    @Override
    protected void onResume() {
        super.onResume();
        //username=getIntent().getStringExtra("username");
        init(username);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
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
