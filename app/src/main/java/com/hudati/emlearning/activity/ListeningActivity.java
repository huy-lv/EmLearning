package com.hudati.emlearning.activity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.hudati.emlearning.R;
import com.hudati.emlearning.adapter.PracticePagerAdapter;
import com.hudati.emlearning.api.APIClient;
import com.hudati.emlearning.api.QuestionResponse;
import com.hudati.emlearning.api.SectionResponse;
import com.hudati.emlearning.base.BaseToolbarActivity;
import com.hudati.emlearning.custom.NSViewPager;
import com.hudati.emlearning.fragment.ListeningFragment;
import com.hudati.emlearning.model.Section;
import com.hudati.emlearning.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hudati.emlearning.util.Utils.FRAGMENT_KEY_PAGE;
import static com.hudati.emlearning.util.Utils.INTENT_KEY_START_LISTENING;

/**
 * Created by huylv on 06-Apr-17.
 */

public class ListeningActivity extends BaseToolbarActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, SeekBar.OnSeekBarChangeListener {

    //    @BindView(R.id.section_list_rv)
//    RecyclerView section_list_rv;
    public ArrayList<Section> sections;
    @BindView(R.id.practice_viewpager)
    NSViewPager practice_viewpager;
    @BindView(R.id.practice_pb)
    ProgressBar practice_pb;
    @BindView(R.id.media_sb)
    SeekBar media_sb;
    @BindView(R.id.media_play)
    ImageView read_play;
    @BindView(R.id.toolbar_bt_next)
    ImageView toolbar_bt_next;
    ArrayList<ListeningFragment> listeningFragments;
    String mp3Url;
    private MediaPlayer mediaPlayer;
    private int mediaFileLengthInMilliseconds;
    private Handler handler = new Handler();
    private PagerAdapter pagerAdapter;
    private int currentPage;
    private int iSection=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sections = new ArrayList<>();
        currentPage = 0;
        mp3Url = getIntent().getStringExtra(Utils.INTENT_KEY_START_LISTENING_MP3);
        Log.e("cxz","mp3url of listening:"+mp3Url);
        //load section
        Call<SectionResponse> call = APIClient.getInterface().loadSections(getIntent().getStringExtra(INTENT_KEY_START_LISTENING));
        call.enqueue(new Callback<SectionResponse>() {
            @Override
            public void onResponse(Call<SectionResponse> call, Response<SectionResponse> response) {
//                Log.e("Cxz","load section:"+response.body().getSections());
                sections.addAll(response.body().getSections());

                //add section to viewpager
                listeningFragments = new ArrayList<>();
                for (int i = 0; i < sections.size(); i++) {
                    ListeningFragment f = new ListeningFragment();
                    Bundle b = new Bundle();
                    b.putInt(FRAGMENT_KEY_PAGE, i);
                    f.setArguments(b);
                    listeningFragments.add(f);
                }

                //load each section
                loadEachSection();

                prepareMediaPlayer();
            }

            @Override
            public void onFailure(Call<SectionResponse> call, Throwable t) {
                Utils.showInfoDialog(ListeningActivity.this, t.getMessage());
            }
        });


        //
        toolbar_bt_next.setVisibility(View.VISIBLE);

    }

    private void prepareMediaPlayer() {

        //play media
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnCompletionListener(this);
        media_sb.setOnSeekBarChangeListener(this);
        try {
            mediaPlayer.setDataSource(mp3Url != null ? mp3Url : ""); // setup song from https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
            mediaPlayer.prepareAsync(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
            play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL

    }

    @OnClick(R.id.media_play)
    void play() {
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
        float a = (float) mediaPlayer.getCurrentPosition();
        float b = ((float)mediaPlayer.getCurrentPosition()/ mediaPlayer.getDuration());
        int currentPos = (int) (b* 100);
        media_sb.setProgress(currentPos); // This math construction give a percentage of "was playing"/"song length"
        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    primarySeekBarProgressUpdater();
                }
            };
            handler.postDelayed(notification, 1000);
        }
    }

    @OnClick(R.id.toolbar_bt_next)
    void clickNext() {
        if (currentPage == listeningFragments.size() - 1) {
            //check point
            Utils.showConfirmDialog(this, "Done!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        } else {
            currentPage++;
            practice_viewpager.setCurrentItem(currentPage, true);
            setActionbarTitle(sections.get(currentPage).getSectionTitle());
            Log.e("Cxz", "title " + sections.get(currentPage).getSectionTitle());
        }
    }

    private void loadEachSection() {
        for (int i = 0; i < sections.size(); i++) {
//            final Section s = sections.get(i);
            if (i == 0) setActionbarTitle(sections.get(0).getSectionTitle());
            Call<QuestionResponse> call = APIClient.getInterface().loadQuestion(sections.get(i).getActions().getActionQuestions());
            call.enqueue(new Callback<QuestionResponse>() {
                @Override
                public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                    if (response.body().getQuestions().size() > 0) {
                        sections.get(iSection).setQuestions(response.body().getQuestions());
                        Log.e("cxz", "load question " + iSection + " " + response.body().getQuestions().get(0).getQuestionTitle());
                    } else {
                        Log.e("cxz", "load question " + iSection + " questions size 0");
                    }

                    iSection++;
                    if (iSection == sections.size() - 1) {
                        onLoadDone();
                    }
                }

                @Override
                public void onFailure(Call<QuestionResponse> call, Throwable t) {
                    Utils.showInfoDialog(ListeningActivity.this, t.getMessage());
                }
            });
        }
        iSection = 0;
    }

    private void onLoadDone() {
        practice_pb.setVisibility(View.GONE);
        //        pagerAdapter.notifyDataSetChanged();
        pagerAdapter = new PracticePagerAdapter(getFragmentManager(), listeningFragments);
        practice_viewpager.setAdapter(pagerAdapter);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_listening;
    }

    @Override
    public void onBackPressed() {
        Utils.showConfirmDialog(this, "Are you sure to quit this test?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
    }

    @Override
    protected void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        read_play.setImageResource(R.drawable.ic_skill_play_iv);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        media_sb.setSecondaryProgress(i);
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
