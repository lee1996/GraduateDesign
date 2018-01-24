package Update;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.leet.graduatedesign.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import Base.BaseActivity;
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
        lefttomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
