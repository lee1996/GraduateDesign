package com.example.leet.graduatedesign;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.List;

import Application.MyApplication;
import Base.BaseActivity;
import Entity.BloodPre;
import Entity.BloodPreDao;
import Entity.BloodType;
import Entity.BloodTypeDao;
import Entity.HeightDao;
import Entity.LeftEye;
import Entity.LeftEyeDao;
import Entity.RightEye;
import Entity.RightEyeDao;
import Entity.UserDao;
import Entity.Weight;
import Entity.WeightDao;
import MyView.RadarMarkerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by leet on 18-3-29.
 */

public class ShowActivity extends BaseActivity {
    @BindView(R.id.detailttomain)
    ImageView detailtomain;
    @BindView(R.id.radarchart)
    RadarChart mChart;

    private final UserDao userDao= MyApplication.getInstances().getDaoSession().getUserDao();
    private final HeightDao heightDao=MyApplication.getInstances().getDaoSession().getHeightDao();
    private final WeightDao weightDao=MyApplication.getInstances().getDaoSession().getWeightDao();
    private final LeftEyeDao leftEyeDao=MyApplication.getInstances().getDaoSession().getLeftEyeDao();
    private final RightEyeDao rightEyeDao=MyApplication.getInstances().getDaoSession().getRightEyeDao();
    private final BloodTypeDao bloodTypeDao=MyApplication.getInstances().getDaoSession().getBloodTypeDao();
    private final BloodPreDao bloodPreDao=MyApplication.getInstances().getDaoSession().getBloodPreDao();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);
        detailtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mChart.setBackgroundColor(Color.rgb(60, 65, 82));

        mChart.getDescription().setEnabled(false);

        mChart.setWebLineWidth(1f);
        mChart.setWebColor(Color.LTGRAY);
        mChart.setWebLineWidthInner(1f);
        mChart.setWebColorInner(Color.LTGRAY);
        mChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart

        setData();

        mChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        XAxis xAxis = mChart.getXAxis();
        //xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"体重", "左眼视力", "右眼视力", "收缩压", "舒张压"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = mChart.getYAxis();
        //yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
       // l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.radar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                for (IDataSet<?> set : mChart.getData().getDataSets())
                    set.setDrawValues(!set.isDrawValuesEnabled());

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (mChart.getData() != null) {
                    mChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
                    mChart.invalidate();
                }
                break;
            }
            case R.id.actionToggleRotate: {
                if (mChart.isRotationEnabled())
                    mChart.setRotationEnabled(false);
                else
                    mChart.setRotationEnabled(true);
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleFilled: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) mChart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlightCircle: {

                ArrayList<IRadarDataSet> sets = (ArrayList<IRadarDataSet>) mChart.getData()
                        .getDataSets();

                for (IRadarDataSet set : sets) {
                    set.setDrawHighlightCircleEnabled(!set.isDrawHighlightCircleEnabled());
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionSave: {
                if (mChart.saveToPath("title" + System.currentTimeMillis(), "")) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();
                break;
            }
            case R.id.actionToggleXLabels: {
                mChart.getXAxis().setEnabled(!mChart.getXAxis().isEnabled());
                mChart.notifyDataSetChanged();
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleYLabels: {

                mChart.getYAxis().setEnabled(!mChart.getYAxis().isEnabled());
                mChart.invalidate();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(1400);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(1400);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(1400, 1400);
                break;
            }
            case R.id.actionToggleSpin: {
                mChart.spin(2000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
                        .EaseInCubic);
                break;
            }
        }
        return true;
    }

    public void setData() {

        float mult = 80;
        float min = 20;
        int cnt = 5;
        String username=getIntent().getStringExtra("username");
        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        List<Weight> listWeight=weightDao.queryBuilder().where(WeightDao.Properties.User.eq(username)).build().list();
        List<LeftEye> listLeftEye=leftEyeDao.queryBuilder().where(LeftEyeDao.Properties.User.eq(username)).build().list();
        List<RightEye> listRightEye=rightEyeDao.queryBuilder().where(RightEyeDao.Properties.User.eq(username)).build().list();
        List<BloodPre> listBloodPre=bloodPreDao.queryBuilder().where(BloodPreDao.Properties.User.eq(username)).build().list();
        if(listWeight.size()>1&&listBloodPre.size()>1&&listLeftEye.size()>1&&listRightEye.size()>1) {
            float weight = 0f;
            for (int i = 0; i < listWeight.size() - 1; i++) {
                weight += Float.parseFloat(listWeight.get(i).getWeight());
            }
            float weightResult = weight / (listWeight.size() - 1);
            entries1.add(new RadarEntry(weightResult));
            entries2.add(new RadarEntry(Float.parseFloat(listWeight.get(listWeight.size() - 1).getWeight())));

            float left = 0f;
            for (int i = 0; i < listLeftEye.size() - 1; i++) {
                left += Float.parseFloat(listLeftEye.get(i).getLefteye());
            }
            float leftResult = left / (listLeftEye.size() - 1) + 50;
            entries1.add(new RadarEntry(leftResult));
            entries2.add(new RadarEntry(Float.parseFloat(listLeftEye.get(listLeftEye.size() - 1).getLefteye()) + 50));

            float right = 0f;
            for (int i = 0; i < listRightEye.size() - 1; i++) {
                right += Float.parseFloat(listRightEye.get(i).getRighteye());
            }
            float rightResult = right / (listRightEye.size() - 1) + 50;
            entries1.add(new RadarEntry(rightResult));
            entries2.add(new RadarEntry(Float.parseFloat(listRightEye.get(listRightEye.size() - 1).getRighteye()) + 50));

            float shousuo = 0f, shuzhang = 0f;
            for (int i = 0; i < listBloodPre.size() - 1; i++) {
                shousuo += Float.parseFloat(listBloodPre.get(i).getShousuo());
                shuzhang += Float.parseFloat(listBloodPre.get(i).getShuzhang());
            }
            float resultShuosuo = shousuo / (listBloodPre.size() - 1) - 50;
            float resultShuzhang = shuzhang / (listBloodPre.size() - 1);
            entries1.add(new RadarEntry(resultShuosuo));
            entries2.add(new RadarEntry(Float.parseFloat(listBloodPre.get(listBloodPre.size() - 1).getShousuo()) - 50));
            entries1.add(new RadarEntry(resultShuzhang));
            entries2.add(new RadarEntry(Float.parseFloat(listBloodPre.get(listBloodPre.size() - 1).getShuzhang())));
        }
        RadarDataSet set1 = new RadarDataSet(entries1, "往期平均");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);

        RadarDataSet set2 = new RadarDataSet(entries2, "最近一次");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
        //data.setValueTypeface(mTfLight);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mChart.setData(data);
        mChart.invalidate();
    }
}
