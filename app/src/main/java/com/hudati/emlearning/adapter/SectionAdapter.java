package com.hudati.emlearning.adapter;

import android.app.Activity;
import android.view.View;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseAdapter;
import com.hudati.emlearning.model.Section;

import java.util.ArrayList;

/**
 * Created by huylv on 06-Apr-17.
 */

public class SectionAdapter extends BaseAdapter<Section,SectionAdapter.SectionVH>{


    public SectionAdapter(Activity a, ArrayList<Section> l) {
        super(a, l);
    }

    @Override
    protected SectionVH onCreateViewHolder(View view) {
        return new SectionVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_section;
    }

    @Override
    protected void onSBindViewHolder(SViewHolder holder, int position, Section section) {
        SectionVH vh = (SectionVH) holder;

    }

    class SectionVH extends BaseAdapter.SViewHolder{

        public SectionVH(View itemView) {
            super(itemView);
        }
    }
}
