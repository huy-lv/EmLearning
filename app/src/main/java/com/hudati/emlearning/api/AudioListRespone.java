package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.AudioGoogle;

import java.util.ArrayList;

/**
 * Created by huylv on 17-Apr-17.
 */

public class AudioListRespone {
    @SerializedName("kind")
    String kind;
    @SerializedName("items")
    ArrayList<AudioGoogle> audioList;

    public String getKind() {
        return kind;
    }

    public ArrayList<AudioGoogle> getAudioList() {
        return audioList;
    }
}
