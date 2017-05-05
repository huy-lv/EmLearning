package com.hudati.emlearning.activity;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.hudati.emlearning.R;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.AudioListRespone;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.dialog.DownloadBookDialog;
import com.hudati.emlearning.util.Utils;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.R.id.download_book_pv;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_BOOK_IMAGE;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_BOOK_MP3;
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
    @BindView(R.id.media_sb)
    SeekBar media_sb;
    @BindView(R.id.media_rewind)
    ImageView read_previous;
    @BindView(R.id.media_play)
    ImageView read_play;
    @BindView(R.id.media_forward)
    ImageView read_forward;
    @BindView(R.id.read_book_sliding_layout)
    SlidingUpPanelLayout read_book_sliding_layout;
    Context c;
    @BindView(R.id.media_list)
    ImageView media_list;
    @BindView(R.id.media_collapse)
    ImageView media_collapse;
    @BindView(R.id.read_book_list_audio)
    RecyclerView read_book_list_audio;
    @BindView(R.id.read_book_audio_list_pb)
    ProgressBar read_book_audio_list_pb;
    @BindView(R.id.read_pdfview)
    PDFView read_pdfview;


    private String fileName;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;
    private String audioUrl;
    private DownloadBookDialog downloadBookDialog;
    private String imageUrl;
    private String pdfPath;
    private String imagePath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        fileName = pdfUrl.substring(pdfUrl.lastIndexOf('/') + 1, pdfUrl.length());
        fileName = getIntent().getStringExtra(INTENT_KEY_BOOK_NAME);
        setActionbarTitle(fileName);
        pdfUrl = getIntent().getStringExtra(INTENT_KEY_BOOK_URL);
        audioUrl = getIntent().getStringExtra(INTENT_KEY_BOOK_MP3);
        imageUrl = getIntent().getStringExtra(INTENT_KEY_BOOK_IMAGE);
        c = this;
        pdfPath = Environment.getExternalStorageDirectory() + "/emlearning/" + fileName + ".pdf";
        imagePath = Environment.getExternalStorageDirectory() + "/emlearning/" + fileName + "." + imageUrl.substring(imageUrl.length() - 3, imageUrl.length());

        if (pdfUrl.startsWith("/storage")) {
            read_pdfview.setVisibility(View.VISIBLE);
            read_wv.setVisibility(View.GONE);
            File f = new File(Environment.getExternalStorageDirectory() + "/emlearning");
            pdfUrl = f.listFiles()[0].getPath();
            read_pdfview.fromFile(new File(pdfUrl)).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    read_pb.setVisibility(View.GONE);
                }
            }).load();

            toolbar_bt_download.setColorFilter(Color.BLUE);
            toolbar_bt_download.setEnabled(false);
        } else {
            read_pdfview.setVisibility(View.GONE);
            read_wv.setVisibility(View.VISIBLE);

            read_wv.getSettings().setJavaScriptEnabled(true);
            read_wv.loadUrl("http://docs.google.com/gview?embedded=true&url=" + pdfUrl);
            read_wv.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    read_pb.setVisibility(View.INVISIBLE);
                }
            });
        }

        //add button download
        toolbar_bt_download.setVisibility(View.VISIBLE);
        toolbar_bt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadBookDialog = new DownloadBookDialog(ReadBookActivity.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        downloadBookDialog.download_book_pv.setVisibility(View.VISIBLE);
                        Ion.with(c).load(pdfUrl)
//                                .progress(new ProgressCallback() {
//                                    @Override
//                                    public void onProgress(long downloaded, long total) {
//                                        Log.e("cxz", "downloaded " + downloaded + " " + total + " " + ((float) downloaded / total));
//                                        downloadBookDialog.download_book_pv.setProgress((float) downloaded / total);
//                                    }
//                                })
                                .write(new File(pdfPath)).setCallback(new FutureCallback<File>() {
                            @Override
                            public void onCompleted(Exception e, File result) {
                                Ion.with(c).load(imageUrl).write(new File(imagePath)).setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File result) {
                                        downloadBookDialog.download_book_pv.setVisibility(View.GONE);
                                        Utils.showInfoDialog(c, "Download done!");
                                        downloadBookDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });
                downloadBookDialog.show();
            }
        });

        //audio list
        read_book_sliding_layout.setTouchEnabled(false);
        //adapter////////////////////////////////////////////////////////////////////////////////////////////////////

        //play media
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        media_sb.setOnSeekBarChangeListener(this);

        //prepare datasource
        if (audioUrl != null) {
            if (audioUrl.contains("drive.google.com")) {
                APIClient.getInterface().loadAudioList(audioUrl).enqueue(new Callback<AudioListRespone>() {
                    @Override
                    public void onResponse(Call<AudioListRespone> call, Response<AudioListRespone> response) {
                        read_book_audio_list_pb.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onFailure(Call<AudioListRespone> call, Throwable t) {

                    }
                });
//            audioUrl = "https://drive.google.com/uc?id=0BxD3fIhb7pyXaEt4SUtDbENkTkk&export=download";
                prepareWithUrl(audioUrl);
                media_collapse.setVisibility(View.VISIBLE);
                media_list.setVisibility(View.VISIBLE);
            } else {
                prepareWithUrl(audioUrl);
            }
        }
    }

    @OnClick(R.id.media_list)
    void showMediaList() {
        read_book_sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    @OnClick(R.id.media_collapse)
    void collapseMediaList() {
        read_book_sliding_layout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    void prepareWithUrl(String url) {
        try {
            mediaPlayer.setDataSource(url);              //mediaPlayer.setDataSource("http://s95.stream.nixcdn.com/bf6e5b0294bab1950d560c5827dd728b/58f2fc02/NhacCuaTui935/Yeu5-Rhymastic-4756973.mp3?t=1492319358395"); // setup song from https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
            mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL
    }

    @OnClick(R.id.media_rewind)
    void rewind() {
        if (mediaPlayer.isPlaying()) {
            int playPositionInMillisecconds = mediaPlayer.getCurrentPosition() - 5000;
            mediaPlayer.seekTo(playPositionInMillisecconds);
        }
    }

    @OnClick(R.id.media_forward)
    void forward() {
        if (mediaPlayer.isPlaying()) {
            int playPositionInMillisecconds = mediaPlayer.getCurrentPosition() + 5000;
            mediaPlayer.seekTo(playPositionInMillisecconds);
        }
    }

    @OnClick(R.id.media_play)
    void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            read_play.setImageResource(R.drawable.ic_pause_black_24dp);
            media_sb.setEnabled(true);
        } else {
            mediaPlayer.pause();
            read_play.setImageResource(R.drawable.ic_skill_play_iv);
        }
        primarySeekBarProgressUpdater();
    }

    private void primarySeekBarProgressUpdater() {
        media_sb.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaFileLengthInMilliseconds) * 100)); // This math construction give a percentage of "was playing"/"song length"
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
        return R.layout.activity_read_book;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        media_sb.setSecondaryProgress(i);
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
            int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * media_sb.getProgress();
            mediaPlayer.seekTo(playPositionInMillisecconds);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.pause();
    }
}
