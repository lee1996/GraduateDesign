package com.example.leet.graduatedesign;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.gyf.barlibrary.ImmersionBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Application.MyApplication;
import Entity.BloodPre;
import Entity.BloodPreDao;
import MyView.MyMarkerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import cc.duduhuo.dialog.smartisan.NormalDialog;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;

/**
 * Created by leet on 18-4-12.
 */

public class BpByMonthActivity extends Activity {
    private ImmersionBar mImmersionBar;
    @BindView(R.id.bpbymonthtomain)
    ImageView bptomain;
    @BindView(R.id.knowledge_month)
    ImageView knowledge;
    private LineChart[] mCharts = new LineChart[2];
    private int datacount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpbymonth);
        ButterKnife.bind(this);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
        bptomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        knowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final NormalDialog dialog = SmartisanDialog.createNormalDialog(BpByMonthActivity.this);
                dialog.setTitle("血压正常值")
                        .setMsg("我国健康青年人在安静状态下上压（收缩压）为100~120mmHg，下压（舒张压）为60~80mmHg，脉搏压为30~40mmHg")
                        .setMsgGravity(Gravity.CENTER)
                        .setLeftBtnText("确定")
                        .show();
                dialog.setOnSelectListener(new NormalDialog.OnSelectListener() {
                    @Override
                    public void onLeftSelect() {
                        dialog.dismiss();
                    }

                    @Override
                    public void onRightSelect() {
                        dialog.dismiss();
                    }
                });
            }
        });


        mCharts[0] = (LineChart) findViewById(R.id.bymonthchart1);
        mCharts[1] = (LineChart) findViewById(R.id.bymonthchart2);


        final BloodPreDao bPreDao= MyApplication.getInstances().getDaoSession().getBloodPreDao();
        String name=getIntent().getStringExtra("username");
        List<BloodPre> lis=bPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(name)).build().list();
        if(lis.size()==0){
            Toast.makeText(BpByMonthActivity.this,"暂无数据",Toast.LENGTH_SHORT).show();

        }else if(lis.size()<30){
            datacount=lis.size();
            try {
                setupChart(mCharts[0], getShuzhangData(), mColors[0]);
                setupChart(mCharts[1], getShousuogData(), mColors[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Toast.makeText(BpByMonthActivity.this,"数据量不够,仅显示"+datacount+"条数据",Toast.LENGTH_SHORT).show();
        }else {
            datacount=30;
            try {
                setupChart(mCharts[0], getShuzhangData(), mColors[0]);
                setupChart(mCharts[1], getShousuogData(), mColors[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }


    private int[] mColors = new int[] {
            Color.rgb(137, 230, 81),
            Color.rgb(89, 199, 250),
    };

    private void setupChart(LineChart mChart, LineData data, int color) {

        ((LineDataSet) data.getDataSetByIndex(0)).setCircleColorHole(color);

        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // set an alternative background color
        mChart.setViewPortOffsets(0f, 0f, 0f, 0f);
        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
        mChart.setMarker(mv);
        // add data
        mChart.setData(data);
        mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        l.setEnabled(true);
        l.setTextColor(Color.WHITE);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        //xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour

        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            final BloodPreDao bloodPreDao= MyApplication.getInstances().getDaoSession().getBloodPreDao();
            String username=getIntent().getStringExtra("username");
            List<BloodPre> list=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                axis.calculate(0,datacount);
                //Date date=new Date();
                Log.i("getformattedvalue","  "+(int)value);
                return sdf.format(list.get(((int) value+2)%list.size()).getTime());

            }
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }
    private LineData getShuzhangData() throws ParseException {
        final BloodPreDao bloodPreDao= MyApplication.getInstances().getDaoSession().getBloodPreDao();
        String username=getIntent().getStringExtra("username");
        List<BloodPre> list=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        Log.i("getshuzhang ","bloodpre is "+list.size());
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int j=0;
        for(int i=list.size()-datacount;i<list.size();i++,j++){
            yVals.add(new Entry(j,Float.parseFloat(list.get(i).getShuzhang())));
            Log.i("getshuzhangdata"," "+Float.parseFloat(list.get(i).getShuzhang()));
        }
        LineDataSet set1 = new LineDataSet(yVals, "最近"+datacount+"次舒张压变化");
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        return data;
    }
    private LineData getShousuogData() throws ParseException {
        final BloodPreDao bloodPreDao= MyApplication.getInstances().getDaoSession().getBloodPreDao();
        String username=getIntent().getStringExtra("username");
        List<BloodPre> list=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        Log.i("getshousuo ","bloodpre is "+list.size());
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        int j=0;
        for(int i=list.size()-datacount;i<list.size();i++,j++){
            yVals.add(new Entry(j,Float.parseFloat(list.get(i).getShousuo())));
            Log.i("getshousuodata"," "+Float.parseFloat(list.get(i).getShousuo()));
        }

        LineDataSet set1 = new LineDataSet(yVals, "最近"+datacount+"次收缩压变化");
        set1.setLineWidth(1.75f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.WHITE);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        // create a data object with the datasets
        LineData data = new LineData(set1);

        return data;
    }
}
