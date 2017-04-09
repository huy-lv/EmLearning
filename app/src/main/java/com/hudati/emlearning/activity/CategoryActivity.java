package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.GroupLectureAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.LectureResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huylv on 22-Mar-17.
 */

public class CategoryActivity extends BaseToolbarActivity {

    @BindView(R.id.practice_list_pb)
    ProgressBar phase_list_pb;

    @BindView(R.id.section_list_rv)
    RecyclerView phase_list_rv;
    GroupLectureAdapter groupAdapter;
    private RecyclerView.Adapter adapter;
    private RecyclerViewExpandableItemManager expMgr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String categoryName = getIntent().getStringExtra(Utils.INTENT_KEY_CATEGORY_NAME);
        setActionbarTitle(categoryName);
        expMgr = new RecyclerViewExpandableItemManager(null);

        groupAdapter = new GroupLectureAdapter(this,expMgr);

        //load data
        String link = getIntent().getStringExtra(Utils.INTENT_KEY_ACTION_LECTURE);
        Call<LectureResponse> call = APIClient.getInterface().loadLectures(link);
        call.enqueue(new Callback<LectureResponse>() {
            @Override
            public void onResponse(Call<LectureResponse> call, Response<LectureResponse> response) {
                ArrayList<LectureResponse.Header> groupHeaders = response.body().getData();
                for (int i = 0; i < groupHeaders.size(); i++) {
                    LectureResponse.GroupLectures groupLectures = groupHeaders.get(i).getHeader();
                    groupLectures.setId(i);
                    groupAdapter.groupLectures.add(groupLectures);
                    adapter.notifyDataSetChanged();
                    groupAdapter.notifyDataSetChanged();
                    phase_list_pb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<LectureResponse> call, Throwable t) {
                Utils.showInfoDialog(CategoryActivity.this, t.getMessage());
            }
        });

        adapter = expMgr.createWrappedAdapter(groupAdapter);
        phase_list_rv.setAdapter(adapter);
        phase_list_rv.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));

    }

    //category activity va practice list activity deu dung practice list layout
    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice_list;
    }
}
