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

public class RightEyeUpdateActivity extends BaseActivity {
    @BindView(R.id.righttomain)
    ImageView righttomain;
    @BindView(R.id.save_righteye)
    ImageView save_righteye;
    @BindView(R.id.update_righteye)
    MaterialEditText udpate_righteye;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_righteye);
        ButterKnife.bind(this);
        righttomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
