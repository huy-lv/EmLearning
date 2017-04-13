package com.hudati.emlearning.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.PracticePagerAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.QuestionResponse;
import com.hudati.emlearning.api.SectionResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.custom.NSViewPager;
import com.hudati.emlearning.fragment.ListeningFragment;
import com.hudati.emlearning.model.Section;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.FRAGMENT_KEY_PAGE;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_START_LISTENING;

/**
 * Created by huylv on 06-Apr-17.
 */

public class ListeningActivity extends BaseToolbarActivity {

    @BindView(R.id.practice_viewpager)
    NSViewPager practice_viewpager;
    @BindView(R.id.practice_pb)
    ProgressBar practice_pb;

    //    @BindView(R.id.section_list_rv)
//    RecyclerView section_list_rv;
    ArrayList<Section> sections;
    @BindView(R.id.toolbar_bt_next)
    ImageView toolbar_bt_next;
    ArrayList<ListeningFragment> listeningFragments;
    private PagerAdapter pagerAdapter;
    private int currentPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sections = new ArrayList<>();
        currentPage = 0;

        //load section
        Call<SectionResponse> call = APIClient.getInterface().loadSections(getIntent().getStringExtra(INTENT_KEY_START_LISTENING));
        call.enqueue(new Callback<SectionResponse>() {
            @Override
            public void onResponse(Call<SectionResponse> call, Response<SectionResponse> response) {
                sections.addAll(response.body().getSections());
                //load each section
                loadEachSection();
            }

            @Override
            public void onFailure(Call<SectionResponse> call, Throwable t) {
                Utils.showInfoDialog(ListeningActivity.this, t.getMessage());
            }
        });

        //add section to viewpager
        listeningFragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ListeningFragment f = new ListeningFragment();
            Bundle b = new Bundle();
            b.putInt(FRAGMENT_KEY_PAGE, i);
            f.setArguments(b);
            listeningFragments.add(f);
        }

        //
        toolbar_bt_next.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.toolbar_bt_next)
    void clickNext() {
        if (currentPage == listeningFragments.size() - 1) {
            //check point
            Utils.showInfoDialog(this, "Done");
        } else {
            currentPage++;
            practice_viewpager.setCurrentItem(currentPage, true);
            setActionbarTitle(sections.get(currentPage).getSectionTitle());
            Log.e("Cxz", "title " + sections.get(currentPage).getSectionTitle());
        }
    }

    private void loadEachSection() {
        for (int i = 0; i < sections.size(); i++) {
            final Section s = sections.get(i);
            if (i == 0) setActionbarTitle(s.getSectionTitle());
            Call<QuestionResponse> call = APIClient.getInterface().loadQuestion(s.getActions().getActionQuestions());
            final int finalI = i;
            call.enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                    if (response.body().getQuestions().size() > 0) {
                        s.setQuestions(response.body().getQuestions());
                    }
                    listeningFragments.get(finalI).setSection(s);
                    if (finalI == sections.size() - 1) {

                        practice_pb.setVisibility(View.GONE);
                        //        pagerAdapter.notifyDataSetChanged();
                        pagerAdapter = new PracticePagerAdapter(getFragmentManager(), listeningFragments);
                        practice_viewpager.setAdapter(pagerAdapter);
                    }

                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    Utils.showInfoDialog(ListeningActivity.this, t.getMessage());
                }
            });
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_listening;
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
