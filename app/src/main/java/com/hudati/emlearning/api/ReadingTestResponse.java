package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huylv on 13-Apr-17.
 */

public class ReadingTestResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<ReadingSection> readingSections;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ReadingSection> getReadingSections() {
        return readingSections;
    }
}
