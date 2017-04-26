package com.hudati.emlearning.custom;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by huylv on 13-Apr-17.
 */

public class PracticeTextView extends android.support.v7.widget.AppCompatTextView {
    Context context;


    public PracticeTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public PracticeTextView(Context context, String text) {
        super(context);
        this.context = context;
        setText(text);
        init();
    }

    public PracticeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        setTextColor(Color.BLACK);
        setTextSize(20);
        setPadding(getPaddingLeft(),10,getPaddingRight(),10);
    }

}
