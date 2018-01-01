package com.example.leet.graduatedesign;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.URIParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Scanner;
import com.mylhyl.zxing.scanner.result.AddressBookResult;


import Base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 17-11-28.
 */

public class ScanActivity extends BaseActivity {
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
        scannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                ParsedResultType type = parsedResult.getType();
                Intent intent;
                switch (type) {
                    case ADDRESSBOOK:
                        AddressBookParsedResult addressBook = (AddressBookParsedResult) parsedResult;
//                        intent=new Intent(ScanActivity.this,MainActivity.class);
//                        startActivity(intent);
                        finish();
                        //bundle.putSerializable(ContactsContract.Intents.Scan.RESULT, new AddressBookResult(addressBook));
                        break;
                    case URI:
                        URIParsedResult uriParsedResult = (URIParsedResult) parsedResult;
                        Toast.makeText(ScanActivity.this,uriParsedResult.getURI(),Toast.LENGTH_SHORT).show();
                        Log.i("TAG",uriParsedResult.getURI());
                        intent=new Intent(ScanActivity.this,CertainActivity.class);
                        startActivity(intent);
                        finish();
                        //bundle.putString(ContactsContract.Intents.Scan.RESULT, uriParsedResult.getURI());
                        break;
                    case TEXT:
                       // bundle.putString(ContactsContract.Intents.Scan.RESULT, rawResult.getText());
                        Log.i("TAG",rawResult.getText());
                        Toast.makeText(ScanActivity.this,rawResult.getText(),Toast.LENGTH_SHORT).show();
                        intent=new Intent(ScanActivity.this,CertainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
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

