package com.hudati.emlearning.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by huylv on 27-Mar-17.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), null);
        activity = getActivity();
        ButterKnife.bind(this, v);
        return v;
    }

    protected abstract int getLayoutId();
}
