package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by huylv on 06-Apr-17.
 */

public class Section {
    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("sectionId")
    String sectionId;
    @SerializedName("sectionTitle")
    String sectionTitle;
    @SerializedName("sectionSubTitle")
    String sectionSubTitle;
    @SerializedName("sectionNumber")
    String sectionNumber;
    @SerializedName("sectionImage")
    String sectionImage;
    @SerializedName("sectionContent")
    String sectionContent;
    @SerializedName("actions")
    Action actions;
    @SerializedName("informationIdRef")
    String informationIdRef;
    ArrayList<Question> questions;

    @Override
    public String toString() {
        return "Section{" +
//                "createdDate=" + createdDate +
//                ", updatedDate=" + updatedDate +
//                ", sectionId='" + sectionId + '\'' +
                ", sectionTitle='" + sectionTitle + '\'' +
//                ", sectionSubTitle='" + sectionSubTitle + '\'' +
//                ", sectionNumber='" + sectionNumber + '\'' +
//                ", sectionImage='" + sectionImage + '\'' +
//                ", sectionContent='" + sectionContent + '\'' +
//                ", actions=" + actions +
//                ", informationIdRef='" + informationIdRef + '\'' +
//                ", questions=" + questions +
                '}';
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public String getSectionSubTitle() {
        return sectionSubTitle;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public String getSectionImage() {
        return sectionImage;
    }

    public String getSectionContent() {
        return sectionContent;
    }

    public Action getActions() {
        return actions;
    }

    public String getInformationIdRef() {
        return informationIdRef;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public class Action {
        @SerializedName("actionQuestions")
        String actionQuestions;

        public String getActionQuestions() {
            return actionQuestions;
        }
    }
}
