package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.PracticeSkill;

import java.util.ArrayList;

/**
 * Created by huylv on 05-Apr-17.
 */

public class PracticeDetailResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<PracticeSkill> data;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<PracticeSkill> getData() {
        return data;
    }
}
