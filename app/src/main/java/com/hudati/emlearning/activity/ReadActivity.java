package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseToolbarActivity;

import butterknife.BindView;

/**
 * Created by huylv on 28-Mar-17.
 */

public class ReadActivity extends BaseToolbarActivity {
    @BindView(R.id.read_wv)
    WebView read_wv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String filename = "http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf";
        read_wv.getSettings().setJavaScriptEnabled(true);
        read_wv.loadUrl("http://docs.google.com/gview?embedded=true&url=" + filename);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }
}
