package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 05-Apr-17.
 */

public class Practice {
    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("practiceId")
    String practiceId;
    @SerializedName("practiceTitle")
    String practiceTitle;
    @SerializedName("practiceSubTitle")
    String practiceSubTitle;
    @SerializedName("practiceImageUrl")
    String practiceImageUrl;
    @SerializedName("organIdRef")
    String organIdRef;
    @SerializedName("actions")
    Action actions;

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public String getPracticeTitle() {
        return practiceTitle;
    }

    public String getPracticeSubTitle() {
        return practiceSubTitle;
    }

    public String getPracticeImageUrl() {
        return practiceImageUrl;
    }

    public String getOrganIdRef() {
        return organIdRef;
    }

    public Action getActions() {
        return actions;
    }

    public class Action{
        @SerializedName("actionDetails")
        String actionDetails;

        public String getActionDetails() {
            return actionDetails;
        }
    }
}
