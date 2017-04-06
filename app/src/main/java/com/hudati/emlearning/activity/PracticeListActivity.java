package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.PracticeAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.PracticeListResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.model.Practice;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PracticeListActivity extends BaseToolbarActivity {
    @BindView(R.id.section_list_rv)
    RecyclerView practice_list_rv;
    @BindView(R.id.practice_list_pb)
    ProgressBar practice_detail_pb;
    PracticeAdapter practiceAdapter;
    public ArrayList<Practice> practices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        practices = new ArrayList<>();
        practiceAdapter = new PracticeAdapter(this,practices);
        int cols = Utils.calculateNumOfColumns(this, getResources().getDimensionPixelSize(R.dimen.item_book_width));
        practice_list_rv.setLayoutManager(new GridLayoutManager(this, cols));
        practice_list_rv.setAdapter(practiceAdapter);

        //load data
        Call<PracticeListResponse> call = APIClient.getInterface().loadPracticeList(Utils.apiList.get_api_practice());
        call.enqueue(new Callback<PracticeListResponse>() {
            @Override
            public void onResponse(Call<PracticeListResponse> call, Response<PracticeListResponse> response) {
                practices.addAll(response.body().getData());
                practiceAdapter.notifyDataSetChanged();
                practice_detail_pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PracticeListResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice_list;
    }
}
