package Update;

import android.media.Image;
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

public class BloodTypeUpdateActivity extends BaseActivity {
    @BindView(R.id.btypetomain)
    ImageView btypetomain;
    @BindView(R.id.save_btype)
    ImageView save_btype;
    @BindView(R.id.update_btype)
    MaterialEditText update_btype;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_bloodtype);
        ButterKnife.bind(this);
        btypetomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
