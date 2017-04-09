package com.hudati.emlearning.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseFragment;

/**
 * Created by huylv on 22-Mar-17.
 */

public class UserFragment extends BaseFragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }
}
