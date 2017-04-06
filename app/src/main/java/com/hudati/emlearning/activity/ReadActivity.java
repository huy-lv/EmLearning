package com.hudati.emlearning.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.util.Utils;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;

import butterknife.BindView;

import static com.hudati.emlearning.util.Utils.INTENT_KEY_BOOK_NAME;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_BOOK_URL;

/**
 * Created by huylv on 28-Mar-17.
 */

public class ReadActivity extends BaseToolbarActivity {
    @BindView(R.id.read_wv)
    WebView read_wv;
    @BindView(R.id.read_pb)
    ProgressBar read_pb;
    @BindView(R.id.toolbar_bt_download)
    ImageView toolbar_bt_download;
    String pdfUrl;
    private String fileName;
    Context c;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionbarTitle(getIntent().getStringExtra(INTENT_KEY_BOOK_NAME));
        pdfUrl = getIntent().getStringExtra(INTENT_KEY_BOOK_URL);
        fileName = pdfUrl.substring(pdfUrl.lastIndexOf('/') + 1, pdfUrl.length());
        c = this;

        read_wv.getSettings().setJavaScriptEnabled(true);
        read_wv.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdfUrl);
        read_wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                read_pb.setVisibility(View.INVISIBLE);

            }
        });

        //add button download
        toolbar_bt_download.setVisibility(View.VISIBLE);
        toolbar_bt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showConfirmDialog(c, "Are you sure to download this file?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String path = Environment.getExternalStorageDirectory() +"/emlearning/"+ fileName;
                        Ion.with(c).load(pdfUrl).write(new File(path)).setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File result) {
                                Utils.showInfoDialog(c,"Download done!");
                            }
                        });
                    }
                });

            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }
}
