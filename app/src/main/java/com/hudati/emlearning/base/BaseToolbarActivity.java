package com.hudati.emlearning.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudati.emlearning.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 22-Mar-17.
 */

public abstract class BaseToolbarActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    protected RelativeLayout toolbar;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        toolbar_back.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.toolbar_back) finish();
        return super.onOptionsItemSelected(item);
    }

    protected void setActionbarTitle(String t){
        toolbar_text.setText(t);
    }

    protected abstract int getLayoutId();

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar_back:
                finish();
                break;
        }
    }
}
