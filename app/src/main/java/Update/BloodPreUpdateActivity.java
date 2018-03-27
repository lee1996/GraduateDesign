package Update;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leet.graduatedesign.MainActivity;
import com.example.leet.graduatedesign.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.BloodPre;
import Entity.BloodPreDao;
import Entity.Height;
import Entity.HeightDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-3-16.
 */

public class BloodPreUpdateActivity extends BaseActivity {
    @BindView(R.id.save_pre)
    ImageView save;
    @BindView(R.id.prettomain)
    ImageView backtomain;
    @BindView(R.id.update_shousuo)
    MaterialEditText update_shousuo;
    @BindView(R.id.update_shuzhang)
    MaterialEditText update_shuzhang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bloodpresurre);
        ButterKnife.bind(this);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final BloodPreDao bloodPreDao= MyApplication.getInstances().getDaoSession().getBloodPreDao();
        final String username=getIntent().getStringExtra("username");
        List<BloodPre> list=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        if(list.size()!=0){
            update_shousuo.setText(list.get(list.size()-1).getShousuo());
            update_shuzhang.setText(list.get(list.size()-1).getShuzhang());
        }
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date writeTime=new java.util.Date();
                Log.i("time"," "+writeTime);
                BloodPre bloodPre=new BloodPre(update_shousuo.getText().toString(),update_shuzhang.getText().toString(),getIntent().getStringExtra("username"),writeTime.getTime());
                bloodPreDao.insert(bloodPre);
                //Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Intent intent=new Intent(BloodPreUpdateActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

    }
}
