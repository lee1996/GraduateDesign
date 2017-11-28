package com.example.leet.graduatedesign;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.mylhyl.zxing.scanner.ScannerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 17-11-28.
 */

public class ScanActivity extends Activity {
    @BindView(R.id.scancamera)
    ScannerView scannerView;
    @BindView(R.id.back)
    ImageView back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        scannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        scannerView.onPause();
        super.onPause();
    }
}

