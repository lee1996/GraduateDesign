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
import Entity.HeightDao;
import Entity.Weight;
import Entity.WeightDao;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-1-24.
 */

public class WeightUpdateActivity extends BaseActivity {
    @BindView(R.id.weighttomain)
    ImageView weighttomain;
    @BindView(R.id.save_weight)
    ImageView save_weight;
    @BindView(R.id.update_weight)
    MaterialEditText update_weight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_weight);
        ButterKnife.bind(this);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final WeightDao weightDao= MyApplication.getInstances().getDaoSession().getWeightDao();
        final String username=getIntent().getStringExtra("username");
        List<Weight> list=weightDao.queryBuilder().where(WeightDao.Properties.User.eq(username)).build().list();
        if(list.size()!=0){
            update_weight.setText(list.get(list.size()-1).getWeight());
        }
        weighttomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                finish();
            }
        });
        save_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date writeTime=new java.util.Date();
                Log.i("time"," "+writeTime);
                Weight weight=new Weight(update_weight.getText().toString(),getIntent().getStringExtra("username"),writeTime.getTime());
                weightDao.insert(weight);
                //Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Intent intent=new Intent(WeightUpdateActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });

    }
}
