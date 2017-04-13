package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 13-Apr-17.
 */

public class ReadingSection {
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
    int sectionNumber;
    @SerializedName("sectionImage")
    String sectionImage;
    @SerializedName("sectionContent")
    String sectionContent;
    @SerializedName("actions")
    Action action;
    @SerializedName("informationIdRef")
    String informationIdRef;

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

    public int getSectionNumber() {
        return sectionNumber;
    }

    public String getSectionImage() {
        return sectionImage;
    }

    public String getSectionContent() {
        return sectionContent;
    }

    public Action getAction() {
        return action;
    }

    public String getInformationIdRef() {
        return informationIdRef;
    }

    public class Action {
        @SerializedName("actionQuestions")
        String actionQuestions;

        public String getActionQuestions() {
            return actionQuestions;
        }
    }
}
