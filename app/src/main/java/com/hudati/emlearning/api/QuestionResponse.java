package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Question;

import java.util.ArrayList;

/**
 * Created by huylv on 06-Apr-17.
 */

public class QuestionResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<Question> questions;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }


}
