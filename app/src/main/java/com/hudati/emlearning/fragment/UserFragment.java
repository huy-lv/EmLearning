package com.hudati.emlearning.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 22-Mar-17.
 */

public class UserFragment extends BaseFragment {
    @BindView(R.id.user_radarchart)
    RadarChart user_radarchart;
    @BindView(R.id.user_linechart)
    LineChart user_linechart;
    @BindView(R.id.user_cover)
    ImageView user_cover;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createChart();

        Bitmap blur = BitmapFactory.decodeResource(getResources(), R.mipmap.cover_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            user_cover.setImageBitmap(Utils.blurRenderScript(activity, blur, 5));
        }
    }

    private void createChart() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            entries.add(new Entry(10 + 10 * i, 10 + 2 * i));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Ladel");
        dataSet.setColor(Color.BLUE);
        LineData lineData = new LineData(dataSet);
        user_linechart.setData(lineData);
        user_linechart.invalidate();

        ArrayList<RadarEntry> radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(30));
        radarEntries.add(new RadarEntry(40));
        radarEntries.add(new RadarEntry(50));
        radarEntries.add(new RadarEntry(40));
        radarEntries.add(new RadarEntry(50));
        RadarDataSet radarDataSet = new RadarDataSet(radarEntries, "LLLL");
        radarDataSet.setColor(Color.RED);
        RadarData radarData = new RadarData(radarDataSet);
        user_radarchart.setData(radarData);
        user_radarchart.setWebColorInner(Color.parseColor("#F45B63"));
        user_radarchart.setBackgroundColor(Color.parseColor("#9e9e9e"));
        XAxis xAxis = user_radarchart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            private String[] mActivities = new String[]{"Ngoại công", "Nội công", "Gân cốt", "Thân pháp", "Nhanh nhẹn"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = user_radarchart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        user_radarchart.invalidate();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }
}
