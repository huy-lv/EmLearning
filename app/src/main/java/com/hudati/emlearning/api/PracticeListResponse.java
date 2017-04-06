package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Practice;

import java.util.ArrayList;

/**
 * Created by huylv on 05-Apr-17.
 */

public class PracticeListResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<Practice> data;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Practice> getData() {
        return data;
    }
}
