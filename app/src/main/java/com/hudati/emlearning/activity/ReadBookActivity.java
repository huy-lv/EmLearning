package com.hudati.emlearning.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.util.Utils;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static com.hudati.emlearning.util.Utils.INTENT_KEY_BOOK_NAME;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_BOOK_URL;

/**
 * Created by huylv on 28-Mar-17.
 */

public class ReadBookActivity extends BaseToolbarActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, SeekBar.OnSeekBarChangeListener {
    private final Handler handler = new Handler();
    @BindView(R.id.read_wv)
    WebView read_wv;
    @BindView(R.id.read_pb)
    ProgressBar read_pb;
    @BindView(R.id.toolbar_bt_download)
    ImageView toolbar_bt_download;
    String pdfUrl;
    @BindView(R.id.read_sb)
    SeekBar read_sb;
    @BindView(R.id.read_rewind)
    ImageView read_previous;
    @BindView(R.id.read_play)
    ImageView read_play;
    @BindView(R.id.read_forward)
    ImageView read_forward;
    Context c;
    private String fileName;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;

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
                        String path = Environment.getExternalStorageDirectory() + "/emlearning/" + fileName;
                        Ion.with(c).load(pdfUrl).write(new File(path)).setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File result) {
                                Utils.showInfoDialog(c, "Download done!");
                            }
                        });
                    }
                });

            }
        });


        //play media
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        read_sb.setOnSeekBarChangeListener(this);
    }


    @OnClick(R.id.read_rewind)
    void rewind() {

    }

    @OnClick(R.id.read_forward)
    void forward() {

    }

    @OnClick(R.id.read_play)
    void play() {
        try {
            mediaPlayer.setDataSource("http://s1mp3.r9s70.vcdn.vn/3fe01ae2f4a61df844b7/978826610756853978?key=aXj5gOeuVFxpiBTP8c1bfA&expires=1491508470&filename=Yeu-5-Rhymastic.mp3"); // setup song from https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
            mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            read_play.setImageResource(R.drawable.ic_pause_black_24dp);
        } else {
            mediaPlayer.pause();
            read_play.setImageResource(R.drawable.ic_skill_play_iv);
        }
        primarySeekBarProgressUpdater();
    }

    private void primarySeekBarProgressUpdater() {
        read_sb.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaFileLengthInMilliseconds) * 100)); // This math construction give a percentage of "was playing"/"song length"
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        read_sb.setSecondaryProgress(i);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        read_play.setImageResource(R.drawable.ic_skill_play_iv);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (mediaPlayer.isPlaying()) {
            int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * read_sb.getProgress();
            mediaPlayer.seekTo(playPositionInMillisecconds);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
    }
}
