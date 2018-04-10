package com.example.leet.graduatedesign;

import android.Manifest;
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
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Scanner;
import com.mylhyl.zxing.scanner.result.AddressBookResult;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
                        String uuid=rawResult.getText().toString();
                        String username=getIntent().getStringExtra("username");
                        try {
                            URL url=new URL("http://118.89.160.240:8080/GraduateDesign/");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(ScanActivity.this,rawResult.getText(),Toast.LENGTH_SHORT).show();
                        intent=new Intent(ScanActivity.this,CertainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.CAMERA).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(ScanActivity.this);
                } else {
                    Toast.makeText(ScanActivity.this, "拍照权限未被允许！", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
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
    private  String downloadUrl(URL url) throws IOException {
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            // Timeout for reading InputStream arbitrarily set to 3000ms.
            connection.setReadTimeout(3000);
            // Timeout for connection.connect() arbitrarily set to 3000ms.
            connection.setConnectTimeout(3000);
            // For this use case, set HTTP method to GET.
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // Already true by default but setting just in case; needs to be true since this request
            // is carrying an input (response) body.
            connection.setDoInput(true);
            // Open communications link (network traffic occurs here).
            connection.connect();
            // Log.d("networkfragment","  just for test");
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            // Retrieve the response body as an InputStream.
            stream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(stream,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = br.readLine())!=null)
            {
                builder.append(line);
            }

            br.close();
            isr.close();
            stream.close();
            result = builder.toString();
            Log.v("result in Fragment is",result);
        } finally {
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }
}

