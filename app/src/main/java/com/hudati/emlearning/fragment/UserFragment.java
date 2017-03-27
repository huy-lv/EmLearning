package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by huylv on 22-Mar-17.
 */

public class UserFragment extends BaseFragment {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }
}
