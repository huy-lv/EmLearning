package com.hudati.emlearning.custom;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by huylv on 13-Apr-17.
 */

public class PracticeEditText extends android.support.v7.widget.AppCompatEditText {
    Context context;

    public PracticeEditText(Context context) {
        super(context);
        init();
    }

    public PracticeEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
        setWidth(250);
    }

}
