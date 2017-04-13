package com.hudati.emlearning.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudati.emlearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 13-Apr-17.
 */

public abstract class BaseFullScreenActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    protected RelativeLayout toolbar;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        toolbar_back.setOnClickListener(this);
    }

    public void setActionbarTitle(String t) {
        toolbar_text.setText(t);
    }

    protected abstract int getLayoutId();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back:
                onClickBack();
                break;
        }
    }

    protected void onClickBack() {
        finish();
    }
}
