package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 06-Apr-17.
 */

public class Question {
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
    QuestionType questionType;
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

    public QuestionType getQuestionType() {
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

    @Override
    public String toString() {
        return "Question{" +
                "createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", questionId='" + questionId + '\'' +
                ", questionStart=" + questionStart +
                ", questionEnd=" + questionEnd +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionSubTitle='" + questionSubTitle + '\'' +
                ", questionType=" + questionType +
                ", questionImage='" + questionImage + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionOptions='" + questionOptions + '\'' +
                ", sectionIdRef='" + sectionIdRef + '\'' +
                '}';
    }
}
