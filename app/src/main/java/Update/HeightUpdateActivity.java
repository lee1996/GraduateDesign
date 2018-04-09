package Update;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.leet.graduatedesign.MainActivity;
import com.example.leet.graduatedesign.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

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
    String username;
    private String lastheight;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                update_height.setText(lastheight);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_height);
        ButterKnife.bind(this);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        final HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
        username=getIntent().getStringExtra("username");
        List<Height> list=heightDao.queryBuilder().where(HeightDao.Properties.User.eq(username)).build().list();
        if(list.size()!=0){
            update_height.setText(list.get(list.size()-1).getHeight());
        }
        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                java.util.Date writeTime=new java.util.Date();
                Log.i("time"," "+writeTime);
                final Height height=new Height(update_height.getText().toString(),getIntent().getStringExtra("username"),writeTime.getTime());
                heightDao.insert(height);
                //Toast.makeText(getApplicationContext(),update_height.getText().toString(),Toast.LENGTH_SHORT).show();
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        URL url1= null;
                        try {
                            url1 = new URL("http://118.89.160.240:8080/GraduateDesign/height.action?username="+username+"&height="+update_height.getText().toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        String result= null;
                        try {
                            result = downloadUrl(url1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.i("height response","  "+result);
                    }
                }).start();
                Intent intent=new Intent(HeightUpdateActivity.this, MainActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                finish();
            }
        });
    }
    /*
    @Override
    protected void onStart() {
        super.onStart();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                URL url= null;
                try {
                    url = new URL("http://118.89.160.240:8080/GraduateDesign/getlast.action?username="+username);
                    String result=downloadUrl(url);
                    JSONObject jsonObject=new JSONObject(result);
                    lastheight=jsonObject.getString("height");
                    Message msg=handler.obtainMessage();
                    msg.what=1;
                    msg.sendToTarget();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Looper.loop();
            }
        }).start();
    }
    */
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
