package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.SectionAdapter;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.model.Section;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 06-Apr-17.
 */

public class PracticeActivity extends BaseToolbarActivity{

    @BindView(R.id.section_list_rv)
    RecyclerView section_list_rv;
    ArrayList<Section> sections;
    SectionAdapter sectionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sections =  new ArrayList<>();
        for(int i=0;i<5;i++){
            sections.add(new Section());
        }
        sectionAdapter = new SectionAdapter(this,sections);
        section_list_rv.setAdapter(sectionAdapter);
        section_list_rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice;
    }
}
