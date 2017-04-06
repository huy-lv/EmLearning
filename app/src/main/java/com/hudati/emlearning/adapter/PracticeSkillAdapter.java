package com.hudati.emlearning.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseAdapter;
import com.hudati.emlearning.dialog.PracticeSkillDetailDialog;
import com.hudati.emlearning.model.PracticeSkill;

import java.util.ArrayList;

import butterknife.BindView;

import static android.graphics.Color.parseColor;

/**
 * Created by huylv on 05-Apr-17.
 */

public class PracticeSkillAdapter extends BaseAdapter<PracticeSkill,PracticeSkillAdapter.PracticeSkillVH>{

    public PracticeSkillAdapter(Activity a, ArrayList<PracticeSkill> l) {
        super(a, l);
    }

    @Override
    protected PracticeSkillVH onCreateViewHolder(View view) {
        return new PracticeSkillVH(view);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_practice_skill;
    }

    @Override
    protected void onSBindViewHolder(SViewHolder holder, int position, final PracticeSkill practiceSkill) {
        PracticeSkillVH vh = (PracticeSkillVH) holder;
        vh.item_skill_tv.setText(practiceSkill.getPracticeType().toString());
        vh.item_skill_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(activity,)
                PracticeSkillDetailDialog p = new PracticeSkillDetailDialog(activity,practiceSkill);
                p.show();
            }
        });
        int res  = R.drawable.ic_listening;
        int color = parseColor("#337ab6");
        switch (practiceSkill.getPracticeType()){
            case Listening:
                res = R.drawable.ic_listening;
                color = parseColor("#337ab6");
                break;
            case Reading:
                res = R.drawable.ic_reading;
                color = parseColor("#5cb65c");
                break;
            case Writing:
                res = R.drawable.ic_writing;
                color = Color.parseColor("#efac4e");
                break;
            case Speaking:
                res = R.drawable.ic_speaking;
                color = Color.parseColor("#d8534f");
                break;
        }
        vh.item_skill_iv.setImageResource(res);
        Drawable d = ContextCompat.getDrawable(activity,R.drawable.ripple);
        d.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        vh.item_skill_rl.setBackground(d);
    }

    class PracticeSkillVH extends BaseAdapter.SViewHolder{
        @BindView(R.id.item_skill_iv)
        ImageView item_skill_iv;
        @BindView(R.id.item_skill_tv)
        TextView item_skill_tv;
        @BindView(R.id.item_skill_rl)
        RelativeLayout item_skill_rl;
        public PracticeSkillVH(View itemView) {
            super(itemView);
        }
    }
}
