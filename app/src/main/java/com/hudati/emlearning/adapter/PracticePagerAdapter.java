package com.hudati.emlearning.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.hudati.emlearning.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by huylv on 13-Apr-17.
 */

public class PracticePagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<? extends BaseFragment> practiceFragments;

    public PracticePagerAdapter(FragmentManager fm, ArrayList<? extends BaseFragment> f) {
        super(fm);
        practiceFragments = f;
    }

    @Override
    public Fragment getItem(int position) {
//            Log.e("Cxz", "get item " + practiceFragments.get(position).getSection());
        return practiceFragments.get(position);
    }

    @Override
    public int getCount() {
        return practiceFragments.size();
    }

}
