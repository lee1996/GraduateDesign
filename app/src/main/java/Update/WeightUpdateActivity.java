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
        weighttomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
