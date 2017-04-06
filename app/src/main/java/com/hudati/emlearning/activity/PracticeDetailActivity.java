package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.PracticeSkillAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.PracticeDetailResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.model.PracticeSkill;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 05-Apr-17.
 */

public class PracticeDetailActivity extends BaseToolbarActivity{
    @BindView(R.id.practice_detail_rv)
    RecyclerView practice_detail_rv;
    @BindView(R.id.practice_detail_pb)
    ProgressBar practice_detail_pb;
    ArrayList<PracticeSkill> practiceSkills;
    PracticeSkillAdapter practiceSkillAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionbarTitle(getIntent().getStringExtra(Utils.INTENT_KEY_PRACTICE_NAME));

        practiceSkills = new ArrayList<>();
        practiceSkillAdapter = new PracticeSkillAdapter(this,practiceSkills);
        practice_detail_rv.setLayoutManager(new LinearLayoutManager(this));
        practice_detail_rv.setAdapter(practiceSkillAdapter);

        //loadData
        Call<PracticeDetailResponse> call = APIClient.getInterface().loadPracticeDetail(getIntent().getStringExtra(Utils.INTENT_KEY_PRACTICE_ACTION));
        call.enqueue(new Callback<PracticeDetailResponse>() {
            @Override
            public void onResponse(Call<PracticeDetailResponse> call, Response<PracticeDetailResponse> response) {

                practiceSkills.addAll(response.body().getData());
                practiceSkillAdapter.notifyDataSetChanged();
                practice_detail_pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PracticeDetailResponse> call, Throwable t) {
                Utils.showInfoDialog(PracticeDetailActivity.this,t.getMessage());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice_detail;
    }
}
