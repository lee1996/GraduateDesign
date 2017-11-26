package com.example.leet.graduatedesign;

import android.app.Activity;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;

import java.util.ArrayList;
import java.util.List;

import LoginAndRegister.LoginFragment;
import LoginAndRegister.RegisterFragment;

/**
 * Created by leet on 17-11-26.
 */

public class LoginActivity extends FragmentActivity {
    FragmentManager fragmentManager=getSupportFragmentManager();
    TextView logintext;
    TextView registtext;
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logintext=findViewById(R.id.logintext);
        registtext=findViewById(R.id.registext);
        LoginFragment loginFragment=new LoginFragment();
        RegisterFragment registerFragment=new RegisterFragment();
        final List<Fragment> list=new ArrayList<Fragment>();
        list.add(loginFragment);
        list.add(registerFragment);
        viewPager=findViewById(R.id.viewpager);
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
//                if(position==0){
//                    logintext.setTextColor(R.color.cpb_blue);
//                    registtext.setTextColor(R.color.cpb_grey);
//                }
//                if(position==1){
//                    logintext.setTextColor(R.color.cpb_grey);
//                    registtext.setTextColor(R.color.cpb_blue);
//                }
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
//        if(viewPager.getCurrentItem()==0){
//            logintext.setTextColor(R.color.cpb_blue);
//            registtext.setTextColor(R.color.cpb_grey);
//        }
//        if(viewPager.getCurrentItem()==1){
//            logintext.setTextColor(R.color.cpb_grey);
//            registtext.setTextColor(R.color.cpb_blue);
//        }
        viewPager.setAdapter(fragmentPagerAdapter);
//
    }
}
