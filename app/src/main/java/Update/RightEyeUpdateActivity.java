package Update;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.leet.graduatedesign.BaseInfoActivity;
import com.example.leet.graduatedesign.MainActivity;
import com.example.leet.graduatedesign.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.Height;
import Entity.HeightDao;
import Entity.RightEye;
import Entity.RightEyeDao;
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
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final RightEyeDao rightEyeDao= MyApplication.getInstances().getDaoSession().getRightEyeDao();
        final String username=getIntent().getStringExtra("username");
        List<RightEye> list=rightEyeDao.queryBuilder().where(RightEyeDao.Properties.User.eq(username)).build().list();
        if(list.size()!=0){
            udpate_righteye.setText(list.get(list.size()-1).getRighteye());
        }
        righttomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                finish();
            }
        });
        save_righteye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date writeTime=new java.util.Date();
                Log.i("time"," "+writeTime);
                RightEye rightEye=new RightEye(udpate_righteye.getText().toString(),getIntent().getStringExtra("username"),writeTime.getTime());
                rightEyeDao.insert(rightEye);
                //Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        URL url1= null;
                        try {
                            url1 = new URL("http://118.89.160.240:8080/GraduateDesign/righteye.action?username="+username+"&right="+udpate_righteye.getText().toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        String result= null;
                        try {
                            result = downloadUrl(url1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("righteye response","  "+result);
                    }
                }).start();
                Intent intent=new Intent(RightEyeUpdateActivity.this, BaseInfoActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });


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
