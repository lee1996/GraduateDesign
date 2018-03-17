package Update;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leet.graduatedesign.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import Base.BaseActivity;
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
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
