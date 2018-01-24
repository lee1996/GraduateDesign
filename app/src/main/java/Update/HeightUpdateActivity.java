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
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
