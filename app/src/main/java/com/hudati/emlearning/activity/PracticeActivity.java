package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.BookExpandableItemAdapter;
import com.hudati.emlearning.adapter.OnListItemClickMessageListener;
import com.hudati.emlearning.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * Created by huylv on 22-Mar-17.
 */

public class PracticeActivity extends BaseToolbarActivity {

    @BindView(R.id.practice_rv)
    RecyclerView practice_rv;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerViewExpandableItemManager expMgr = new RecyclerViewExpandableItemManager(null);


        adapter = new BookExpandableItemAdapter(expMgr, new OnListItemClickMessageListener() {
            @Override
            public void onItemClicked(String message) {

            }
        });
        adapter = expMgr.createWrappedAdapter(adapter);
//        adapter = new DemoHeaderFooterAdapter(adapter, clickListener);

        practice_rv.setAdapter(adapter);

        practice_rv.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice;
    }
}
