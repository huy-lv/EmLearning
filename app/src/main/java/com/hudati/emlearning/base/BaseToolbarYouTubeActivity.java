/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hudati.emlearning.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.hudati.emlearning.R;
import com.hudati.emlearning.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hudati.emlearning.R.id.toolbar_back;

/**
 * An abstract activity which deals with recovering from errors which may occur during API
 * initialization, but can be corrected through user action.
 */
public abstract class BaseToolbarYouTubeActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener,View.OnClickListener {
    @BindView(R.id.toolbar)
    RelativeLayout toolbar;
    @BindView(R.id.toolbar_back)
    ImageView toolbar_back;
    @BindView(R.id.toolbar_text)
    TextView toolbar_text;

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        toolbar_back.setOnClickListener(this);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(getString(R.string.error_player), errorReason.toString());
            Log.e("cxz", "cc" + errorMessage);
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Utils.YOUTUBE_DEVELOPER_KEY, this);
        }
    }

    protected abstract YouTubePlayer.Provider getYouTubePlayerProvider();

    protected void setActionbarTitle(String t){
        toolbar_text.setText(t);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.toolbar_back:
                finish();
                break;
        }
    }

    protected abstract int getLayoutId();
}
