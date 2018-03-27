package Update;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.leet.graduatedesign.MainActivity;
import com.example.leet.graduatedesign.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.Height;
import Entity.LeftEye;
import Entity.LeftEyeDao;
import Entity.Weight;
import Entity.WeightDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-1-24.
 */

public class LeftEyeUpdateActivity extends BaseActivity {
    @BindView(R.id.leftttomain)
    ImageView lefttomain;
    @BindView(R.id.save_lefteye)
    ImageView save_lefteye;
    @BindView(R.id.update_lefteye)
    MaterialEditText update_lefteye;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_lefteye);
        ButterKnife.bind(this);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final LeftEyeDao leftEyeDao= MyApplication.getInstances().getDaoSession().getLeftEyeDao();
        final String username=getIntent().getStringExtra("username");
        List<LeftEye> list=leftEyeDao.queryBuilder().where(LeftEyeDao.Properties.User.eq(username)).build().list();
        if(list.size()!=0){
            update_lefteye.setText(list.get(list.size()-1).getLefteye());
        }
        lefttomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save_lefteye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date writeTime=new java.util.Date();
                Log.i("time"," "+writeTime);
                LeftEye leftEye=new LeftEye(update_lefteye.getText().toString(),getIntent().getStringExtra("username"),writeTime.getTime());
                leftEyeDao.insert(leftEye);
                //Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Intent intent=new Intent(LeftEyeUpdateActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
    }
}
