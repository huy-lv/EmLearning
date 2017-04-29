package com.hudati.emlearning.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.PracticePagerAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.ReadingSection;
import com.hudati.emlearning.api.ReadingTestResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.custom.NSViewPager;
import com.hudati.emlearning.fragment.ReadingFragment;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.FRAGMENT_KEY_PAGE;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_START_READING;

/**
 * Created by huylv on 13-Apr-17.
 */

public class ReadingActivity extends BaseToolbarActivity {
    @BindView(R.id.practice_viewpager)
    NSViewPager practice_viewpager;
    ArrayList<ReadingSection> readingSections;
    ArrayList<ReadingFragment> readingFragments;
    @BindView(R.id.toolbar_bt_next)
    ImageView toolbar_bt_next;
    @BindView(R.id.reading_pb)
    ProgressBar reading_pb;
    private int currentPage;
    private PracticePagerAdapter pagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //init
        currentPage = 0;
        readingFragments = new ArrayList<>();


        APIClient.getInterface().loadReadingTest(getIntent().getStringExtra(INTENT_KEY_START_READING)).enqueue(new Callback<ReadingTestResponse>() {
            @Override
            public void onResponse(Call<ReadingTestResponse> call, Response<ReadingTestResponse> response) {
                reading_pb.setVisibility(View.INVISIBLE);
                readingSections = response.body().getReadingSections();
                setActionbarTitle(readingSections.get(0).getSectionTitle());
                //add section to viewpager
                for (int i = 0; i < readingSections.size(); i++) {
                    ReadingFragment f = new ReadingFragment();
                    Bundle b = new Bundle();
                    b.putInt(FRAGMENT_KEY_PAGE, i);
                    f.setArguments(b);
                    f.setSection(readingSections.get(i));
                    readingFragments.add(f);
                }

                pagerAdapter = new PracticePagerAdapter(getFragmentManager(), readingFragments);
                practice_viewpager.setAdapter(pagerAdapter);
            }

            @Override
            public void onFailure(Call<ReadingTestResponse> call, Throwable t) {

            }
        });

        //
        toolbar_bt_next.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.toolbar_bt_next)
    void onClickNext() {
        if (currentPage == readingFragments.size() - 1) {
            //check point
            Utils.showConfirmDialog(this, "Done!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        } else {
            currentPage++;
            practice_viewpager.setCurrentItem(currentPage, true);
            setActionbarTitle(readingSections.get(currentPage).getSectionTitle());
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reading;
    }

    @Override
    public void onBackPressed() {
        Utils.showConfirmDialog(this, "Are you sure to quit this test?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
    }

    @Override
    protected void onClickBack() {
        onBackPressed();
    }
}
