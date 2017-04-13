package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.ReadingQuestion;

import java.util.ArrayList;

/**
 * Created by huylv on 13-Apr-17.
 */

public class ReadingQuestionResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<ReadingQuestion> data;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<ReadingQuestion> getData() {
        return data;
    }


}
