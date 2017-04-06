package com.hudati.emlearning.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.hudati.emlearning.R;
import com.hudati.emlearning.base.BaseToolbarYouTubeActivity;
import com.hudati.emlearning.util.Utils;

import butterknife.BindView;

/**
 * Created by huylv on 05-Apr-17.
 */

public class LectureActivity extends BaseToolbarYouTubeActivity {
    @BindView(R.id.lecture_wv)
    WebView lecture_wv;
    @BindView(R.id.lecture_pb)
    ProgressBar lecture_pb;
    @BindView(R.id.lecture_youtube_view)
    YouTubePlayerView lecture_youtube_view;
    private String lectureYoutubeCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String lectureUrl = getIntent().getStringExtra(Utils.INTENT_KEY_ACTION_LECTURE_PAGE);
        String lectureTitle = getIntent().getStringExtra(Utils.INTENT_KEY_ACTION_LECTURE_NAME);
        lectureYoutubeCode = getIntent().getStringExtra(Utils.INTENT_ACTION_LECTURE_YOUTUBE);
        setActionbarTitle(lectureTitle);

        lecture_wv.getSettings().setJavaScriptEnabled(true);
        lecture_wv.loadUrl(lectureUrl);
        lecture_wv.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                lecture_pb.setVisibility(View.INVISIBLE);
            }
        });

        lecture_youtube_view.initialize(Utils.YOUTUBE_DEVELOPER_KEY, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lecture;
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return lecture_youtube_view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(lectureYoutubeCode);
        }
    }
}
