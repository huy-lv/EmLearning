package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.util.Utils;

/**
 * Created by huylv on 05-Apr-17.
 */

public class PracticeSkill {
    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("practiceId")
    String practiceId;
    @SerializedName("practiceTitle")
    String practiceTitle;
    @SerializedName("practiceDescription")
    String practiceDescription;
    @SerializedName("practiceSound")
    String practiceSound;
    @SerializedName("practiceImageUrl")
    String practiceImageUrl;
    @SerializedName("practiceTime")
    int practiceTime;
    @SerializedName("practiceNumberQuestion")
    int practiceNumberQuestion;
    @SerializedName("actions")
    Action actions;
    @SerializedName("practiceType")
    Utils.PracticeType practiceType;
    @SerializedName("practiceIdRef")
    String practiceIdRef;

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

    public String getPracticeDescription() {
        return practiceDescription;
    }

    public String getPracticeSound() {
        return practiceSound;
    }

    public String getPracticeImageUrl() {
        return practiceImageUrl;
    }

    public int getPracticeTime() {
        return practiceTime;
    }

    public int getPracticeNumberQuestion() {
        return practiceNumberQuestion;
    }

    public Action getActions() {
        return actions;
    }

    public Utils.PracticeType getPracticeType() {
        return practiceType;
    }

    public String getPracticeIdRef() {
        return practiceIdRef;
    }

    public class Action {
        @SerializedName("actionPractice")
        String actionPractice;

        public String getActionPractice() {
            return actionPractice;
        }
    }
}
