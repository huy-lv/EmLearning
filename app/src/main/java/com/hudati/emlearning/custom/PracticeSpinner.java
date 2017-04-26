package com.hudati.emlearning.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by huylv on 26-Apr-17.
 */

public class PracticeSpinner extends android.support.v7.widget.AppCompatSpinner {
    Context context;

    public PracticeSpinner(Context context) {
        super(context);
        init();
    }

    public PracticeSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setMinimumWidth(80);
        setMinimumHeight(20);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, w/2, oldw, oldh);
    }
}
