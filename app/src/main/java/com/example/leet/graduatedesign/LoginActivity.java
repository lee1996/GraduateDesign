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

import Base.BaseActivity;
import LoginAndRegister.LoginFragment;
import LoginAndRegister.RegisterFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 17-11-26.
 */

public class LoginActivity extends FragmentActivity {
    FragmentManager fragmentManager=getSupportFragmentManager();
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.registext)
    TextView registtext;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginFragment loginFragment=new LoginFragment();
        RegisterFragment registerFragment=new RegisterFragment();
        final List<Fragment> list=new ArrayList<Fragment>();
        list.add(loginFragment);
        list.add(registerFragment);
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
        viewPager.setAdapter(fragmentPagerAdapter);
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        registtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
    }
}
