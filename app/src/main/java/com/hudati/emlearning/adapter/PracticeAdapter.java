package com.hudati.emlearning.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.activity.PracticeDetailActivity;
import com.hudati.emlearning.base.BaseAdapter;
import com.hudati.emlearning.model.Practice;
import com.hudati.emlearning.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by huylv on 05-Apr-17.
 */

public class PracticeAdapter extends BaseAdapter<Practice,PracticeAdapter.PracticeVH> {
    public PracticeAdapter(Activity c, ArrayList<Practice> practice_list) {
        super(c,practice_list);
    }

    @Override
    protected PracticeVH onCreateViewHolder(View view) {
        return new PracticeVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_book;
    }

    @Override
    protected void onSBindViewHolder(SViewHolder holder, int position, final Practice practice) {
        PracticeVH practiceVH = (PracticeVH) holder;
        practiceVH.book_name.setText(practice.getPracticeTitle());
        Picasso.with(activity).load(practice.getPracticeImageUrl()).into(practiceVH.book_image);
        practiceVH.book_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, PracticeDetailActivity.class);
                i.putExtra(Utils.INTENT_KEY_PRACTICE_TITLE,practice.getPracticeTitle());
                i.putExtra(Utils.INTENT_KEY_PRACTICE_SUBTITLE,practice.getPracticeSubTitle());
                i.putExtra(Utils.INTENT_KEY_PRACTICE_ACTION,practice.getActions().getActionDetails());
                activity.startActivity(i);
            }
        });
    }

    class PracticeVH extends BaseAdapter.SViewHolder{
        @BindView(R.id.book_name)
        TextView book_name;
        @BindView(R.id.book_image)
        ImageView book_image;
        @BindView(R.id.book_cv)
        CardView book_cv;
        public PracticeVH(View itemView) {
            super(itemView);
        }
    }
}
