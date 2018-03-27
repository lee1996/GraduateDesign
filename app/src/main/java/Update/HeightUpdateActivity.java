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
import Entity.Height;
import Entity.HeightDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-1-24.
 */

public class HeightUpdateActivity extends BaseActivity {
    @BindView(R.id.save_height)
    ImageView save;
    @BindView(R.id.heighttomain)
    ImageView backtomain;
    @BindView(R.id.update_height)
    MaterialEditText update_height;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_height);
        ButterKnife.bind(this);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
        final String username=getIntent().getStringExtra("username");
        List<Height> list=heightDao.queryBuilder().where(HeightDao.Properties.User.eq(username)).build().list();
        if(list.size()!=0){
            update_height.setText(list.get(list.size()-1).getHeight());
        }
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date writeTime=new java.util.Date();
                Log.i("time"," "+writeTime);
                Height height=new Height(update_height.getText().toString(),getIntent().getStringExtra("username"),writeTime.getTime());
                heightDao.insert(height);
                //Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Intent intent=new Intent(HeightUpdateActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

    }
}
