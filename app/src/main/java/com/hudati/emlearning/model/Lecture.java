package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 03-Apr-17.
 */

public class Lecture {

    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("lectureId")
    String lectureId;
    @SerializedName("lectureTitle")
    String lectureTitle;
    @SerializedName("lectureSubTitle")
    String lectureSubTitle;
    @SerializedName("lectureImageUrl")
    String lectureImageUrl;
    @SerializedName("lectureYoutubeLink")
    String lectureYoutubeLink;
    @SerializedName("lectureYoutubeCode")
    String lectureYoutubeCode;
    @SerializedName("lectureMp3Link")
    String lectureMp3Link;
    @SerializedName("lectureUnit")
    String lectureUnit;
    @SerializedName("headerIdRef")
    String headerIdRef;
    @SerializedName("actions")
    Action actions;

    public class Action {
        @SerializedName("actionSinglePage")
        String actionSinglePage;

        public String getActionSinglePage() {
            return actionSinglePage;
        }
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public String getLectureId() {
        return lectureId;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public String getLectureSubTitle() {
        return lectureSubTitle;
    }

    public String getLectureImageUrl() {
        return lectureImageUrl;
    }

    public String getLectureYoutubeLink() {
        return lectureYoutubeLink;
    }

    public String getLectureYoutubeCode() {
        return lectureYoutubeCode;
    }

    public String getLectureMp3Link() {
        return lectureMp3Link;
    }

    public String getLectureUnit() {
        return lectureUnit;
    }

    public String getHeaderIdRef() {
        return headerIdRef;
    }

    public Action getActions() {
        return actions;
    }
}
