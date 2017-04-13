package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 13-Apr-17.
 */

public class ReadingQuestion {
    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("questionId")
    String questionId;
    @SerializedName("questionStart")
    int questionStart;
    @SerializedName("questionEnd")
    int questionEnd;
    @SerializedName("questionTitle")
    String questionTitle;
    @SerializedName("questionSubTitle")
    String questionSubTitle;

    @SerializedName("questionType")
    String questionType;
    @SerializedName("questionImage")
    String questionImage;
    @SerializedName("questionContent")
    String questionContent;
    @SerializedName("questionOptions")
    String questionOptions;
    @SerializedName("sectionIdRef")
    String sectionIdRef;

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public String getQuestionId() {
        return questionId;
    }

    public int getQuestionStart() {
        return questionStart;
    }

    public int getQuestionEnd() {
        return questionEnd;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionSubTitle() {
        return questionSubTitle;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public String getSectionIdRef() {
        return sectionIdRef;
    }
}
