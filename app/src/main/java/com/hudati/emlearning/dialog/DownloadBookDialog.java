package com.hudati.emlearning.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.hudati.emlearning.R;
import com.rey.material.widget.ProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huylv on 04-May-17.
 */

public class DownloadBookDialog extends Dialog {

    @BindView(R.id.download_book_pv)
    public ProgressView download_book_pv;
    @BindView(R.id.download_book_cancel)
    Button download_book_cancel;
    @BindView(R.id.download_book_ok)
    Button download_book_ok;
    View.OnClickListener onClick;

    public DownloadBookDialog(@NonNull Context context, View.OnClickListener onClick) {
        super(context);
        this.onClick = onClick;
        init();
    }

    public DownloadBookDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected DownloadBookDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_download_book);
        ButterKnife.bind(this);

        download_book_ok.setOnClickListener(onClick);
        download_book_pv.setProgress(30);
    }
}
