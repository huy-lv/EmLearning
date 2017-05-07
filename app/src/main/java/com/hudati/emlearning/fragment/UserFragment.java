package com.hudati.emlearning.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
import com.google.gson.Gson;
import com.hudati.emlearning.R;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.BaseResponse;
import com.hudati.emlearning.api.LoginResponse;
import com.hudati.emlearning.base.BaseFragment;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.LOGGED_IN;
import static com.hudati.emlearning.util.Utils.getCurrentUser;
import static com.hudati.emlearning.util.Utils.showInfoDialog;

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
    @BindView(R.id.user_logged_in)
    LinearLayout user_logged_in;
    @BindView(R.id.user_input_user_name)
    EditText user_input_user_name;
    @BindView(R.id.user_input_password)
    EditText user_input_password;
    @BindView(R.id.user_login_bt)
    Button user_login_bt;
    @BindView(R.id.user_login)
    LinearLayout user_login;
    @BindView(R.id.user_logout)
    Button user_logout;
    ProgressDialog progressDialog;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (LOGGED_IN) {
            user_logged_in.setVisibility(View.VISIBLE);
            user_login.setVisibility(View.GONE);

            initViewUser();

        } else {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading");

            user_logged_in.setVisibility(View.GONE);
            user_login.setVisibility(View.VISIBLE);
        }
    }

    private void initViewUser() {
        createChart();

        Bitmap blur = BitmapFactory.decodeResource(getResources(), R.mipmap.cover_profile);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            user_cover.setImageBitmap(Utils.blurRenderScript(activity, blur, 5));
        }
    }

    @OnClick(R.id.user_login_bt)
    public void onClickLogin() {
        String username = user_input_user_name.getText().toString().trim();
        String password = user_input_password.getText().toString().trim();
        if (username.equals(""))
            user_input_user_name.setError("Please enter username!");
        else if (password.equals(""))
            user_input_password.setError("Please enter password!");
        else if (username.length() < 5 || password.length() < 5)
            showInfoDialog(getActivity(), getString(R.string.wrong_username));
        else {
            progressDialog.show();
            Call<LoginResponse> call = APIClient.getInterface().login(Utils.apiList.get_api_user_login(), username, password, null, null);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()) {
                        Utils.setCurrentUser(response.body().getUser());
                        LOGGED_IN = true;
                        user_logged_in.setVisibility(View.VISIBLE);
                        user_login.setVisibility(View.GONE);
                        initViewUser();
                        saveLoginToSP();

                        user_input_user_name.clearComposingText();
                        user_input_password.clearComposingText();
                    } else {
                        showInfoDialog(getActivity(), response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    showInfoDialog(getActivity(), t.getMessage());
                }
            });
        }
    }

    private void saveLoginToSP() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(Utils.SP_CHECK_USER, (new Gson()).toJson(getCurrentUser()));
        ed.apply();
    }

    @OnClick(R.id.user_logout)
    void onClickLogout() {
        Utils.showConfirmDialog(getActivity(), "Are you sure to log out?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Call<BaseResponse> call = APIClient.getInterface().logout(Utils.apiList.get_api_user_logout(), getCurrentUser().getAccessToken());
                call.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getError() == 0) {
                                LOGGED_IN = false;
                                Utils.setCurrentUser(null);
                                clearLoginFromSP();

                                user_login.setVisibility(View.VISIBLE);
                                user_logged_in.setVisibility(View.GONE);
                            } else {
                                Utils.showInfoDialog(getActivity(), response.errorBody().toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Utils.showInfoDialog(getActivity(), t.getMessage());
                    }
                });
            }
        });

    }

    private void clearLoginFromSP() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sp.edit().clear().apply();
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
