package com.example.leet.graduatedesign;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.gyf.barlibrary.ImmersionBar;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Application.MyApplication;
import Entity.BloodPre;
import Entity.BloodPreDao;
import butterknife.BindView;
import butterknife.ButterKnife;
import cc.duduhuo.dialog.smartisan.NormalDialog;
import cc.duduhuo.dialog.smartisan.SmartisanDialog;

/**
 * Created by leet on 18-4-12.
 */

public class BpByDayActivity extends Activity {
    private ImmersionBar mImmersionBar;
    @BindView(R.id.bptomain)
    ImageView bptomain;
    @BindView(R.id.knowledge)
    ImageView knowledge;
    @BindView(R.id.shousuo)
    BarChart shousuo;
    @BindView(R.id.shuzhang)
    BarChart shuzhang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpbyday);
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
                final NormalDialog dialog = SmartisanDialog.createNormalDialog(BpByDayActivity.this);
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
        final BloodPreDao bloodPreDao= MyApplication.getInstances().getDaoSession().getBloodPreDao();
        final String username=getIntent().getStringExtra("username");
        List<BloodPre> list=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        // sdf.format(writeTime);
        //Log.i("time"," "+sdf.format(writeTime));
        shuzhang.setBackgroundColor(Color.WHITE);
        shuzhang.setExtraTopOffset(-30f);
        shuzhang.setExtraBottomOffset(10f);
        shuzhang.setExtraLeftOffset(70f);
        shuzhang.setExtraRightOffset(70f);

        shuzhang.setDrawBarShadow(false);
        shuzhang.setDrawValueAboveBar(true);

        shuzhang.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        shuzhang.setPinchZoom(false);

        shuzhang.setDrawGridBackground(false);

        XAxis shuzhangXAxis = shuzhang.getXAxis();
        shuzhangXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTypeface(mTf);
        shuzhangXAxis.setDrawGridLines(false);
        shuzhangXAxis.setDrawAxisLine(false);
        shuzhangXAxis.setTextColor(Color.LTGRAY);
        shuzhangXAxis.setTextSize(13f);
        shuzhangXAxis.setLabelCount(5);
        shuzhangXAxis.setCenterAxisLabels(true);
        shuzhangXAxis.setGranularity(1f);

        YAxis shuzhangleft = shuzhang.getAxisLeft();
        shuzhangleft.setDrawLabels(false);
        shuzhangleft.setSpaceTop(25f);
        shuzhangleft.setSpaceBottom(25f);
        shuzhangleft.setDrawAxisLine(false);
        shuzhangleft.setDrawGridLines(false);
        shuzhangleft.setDrawZeroLine(true); // draw a zero line
        shuzhangleft.setZeroLineColor(Color.GRAY);
        shuzhangleft.setZeroLineWidth(0.7f);
        shuzhang.getAxisRight().setEnabled(false);
        shuzhang.getLegend().setEnabled(false);

        //收缩压
        shousuo.setBackgroundColor(Color.WHITE);
        shousuo.setExtraTopOffset(-30f);
        shousuo.setExtraBottomOffset(10f);
        shousuo.setExtraLeftOffset(70f);
        shousuo.setExtraRightOffset(70f);

        shousuo.setDrawBarShadow(false);
        shousuo.setDrawValueAboveBar(true);

        shousuo.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        shousuo.setPinchZoom(false);

        shousuo.setDrawGridBackground(false);

        XAxis shousuoxAxis = shousuo.getXAxis();
        shousuoxAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setTypeface(mTf);
        shousuoxAxis.setDrawGridLines(false);
        shousuoxAxis.setDrawAxisLine(false);
        shousuoxAxis.setTextColor(Color.LTGRAY);
        shousuoxAxis.setTextSize(13f);
        shousuoxAxis.setLabelCount(5);
        shousuoxAxis.setCenterAxisLabels(true);
        shousuoxAxis.setGranularity(1f);

        YAxis shousuoleft = shousuo.getAxisLeft();
        shousuoleft.setDrawLabels(false);
        shousuoleft.setSpaceTop(25f);
        shousuoleft.setSpaceBottom(25f);
        shousuoleft.setDrawAxisLine(false);
        shousuoleft.setDrawGridLines(false);
        shousuoleft.setDrawZeroLine(true); // draw a zero line
        shousuoleft.setZeroLineColor(Color.GRAY);
        shousuoleft.setZeroLineWidth(0.7f);
        shousuo.getAxisRight().setEnabled(false);
        shousuo.getLegend().setEnabled(false);

        // THIS IS THE ORIGINAL DATA YOU WANT TO PLOT
        final List<BpByDayActivity.Data> shuzhangData = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            shuzhangData.add(new BpByDayActivity.Data((float)i,Float.parseFloat(list.get(i).getShuzhang()),sdf.format(list.get(i).getTime())));
        }
        final List<BpByDayActivity.Data> shousuoData=new ArrayList<>();
        for(int i=0;i<list.size();i++){
            shousuoData.add(new BpByDayActivity.Data((float)i,Float.parseFloat(list.get(i).getShousuo()),sdf.format(list.get(i).getTime())));
        }

        shuzhangXAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return shuzhangData.get(Math.min(Math.max((int) value, 0), shuzhangData.size()-1)).xAxisValue;
            }
        });
        setShuzhangData(shuzhangData);

        shousuoxAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return shousuoData.get(Math.min(Math.max((int) value, 0), shousuoData.size()-1)).xAxisValue;
            }
        });
        setShousuoData(shousuoData);

    }

    private void setShuzhangData(List<BpByDayActivity.Data> dataList) {

        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        List<Integer> colors = new ArrayList<Integer>();

        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);

        for (int i = 0; i < dataList.size(); i++) {

            BpByDayActivity.Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue);
            values.add(entry);

            // specific colors
            if (d.yValue >= 80||d.yValue<=60)
                colors.add(red);
            else
                colors.add(green);
        }

        BarDataSet set;

        if (shuzhang.getData() != null &&
                shuzhang.getData().getDataSetCount() > 0) {
            set = (BarDataSet)shuzhang.getData().getDataSetByIndex(0);
            set.setValues(values);
            shuzhang.getData().notifyDataChanged();
            shuzhang.notifyDataSetChanged();
        } else {
            set = new BarDataSet(values, "Values");
            set.setColors(colors);
            set.setValueTextColors(colors);

            BarData data = new BarData(set);
            data.setValueTextSize(13f);
            //data.setValueTypeface(mTf);
            data.setValueFormatter(new BpByDayActivity.ValueFormatter());
            data.setBarWidth(0.8f);

            shuzhang.setData(data);
            shuzhang.invalidate();
        }
    }
    private void setShousuoData(List<BpByDayActivity.Data> dataList) {

        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        List<Integer> colors = new ArrayList<Integer>();

        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);

        for (int i = 0; i < dataList.size(); i++) {

            BpByDayActivity.Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue);
            values.add(entry);

            // specific colors
            if (d.yValue >= 120||d.yValue<=100)
                colors.add(red);
            else
                colors.add(green);
        }

        BarDataSet set;

        if (shousuo.getData() != null &&
                shousuo.getData().getDataSetCount() > 0) {
            set = (BarDataSet)shousuo.getData().getDataSetByIndex(0);
            set.setValues(values);
            shousuo.getData().notifyDataChanged();
            shousuo.notifyDataSetChanged();
        } else {
            set = new BarDataSet(values, "Values");
            set.setColors(colors);
            set.setValueTextColors(colors);

            BarData data = new BarData(set);
            data.setValueTextSize(13f);
            //data.setValueTypeface(mTf);
            data.setValueFormatter(new BpByDayActivity.ValueFormatter());
            data.setBarWidth(0.8f);

            shousuo.setData(data);
            shousuo.invalidate();
        }
    }
    private class Data {

        public String xAxisValue;
        public float yValue;
        public float xValue;

        public Data(float xValue, float yValue, String xAxisValue) {
            this.xAxisValue = xAxisValue;
            this.yValue = yValue;
            this.xValue = xValue;
        }
    }

    private class ValueFormatter implements IValueFormatter
    {

        private DecimalFormat mFormat;

        public ValueFormatter() {
            mFormat = new DecimalFormat("######.0");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

}
