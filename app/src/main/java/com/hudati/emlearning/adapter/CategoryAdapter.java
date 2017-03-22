package com.hudati.emlearning.adapter;

import android.app.Activity;
import android.view.View;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseAdapter;
import com.hudati.emlearning.model.Category;

import java.util.ArrayList;

/**
 * Created by huylv on 22-Mar-17.
 */

public class CategoryAdapter extends BaseAdapter<Category,CategoryAdapter.CategoryVH> {

    public CategoryAdapter(Activity a, ArrayList<Category> l) {
        super(a, l);
    }

    @Override
    protected CategoryVH onCreateViewHolder(View view) {
        return new CategoryVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_category;
    }

    @Override
    protected void onSBindViewHolder(SViewHolder holder, int position, Category category) {

    }

    class CategoryVH extends BaseAdapter.SViewHolder {

        public CategoryVH(View itemView) {
            super(itemView);
        }
    }
}
