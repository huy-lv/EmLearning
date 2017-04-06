package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 22-Mar-17.
 */

public class Category {
    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("isDeleted")
    boolean isDeleted;
    @SerializedName("categoryId")
    String categoryId;
    @SerializedName("categoryName")
    String categoryName;
    @SerializedName("categoryImageUrl")
    String categoryImageUrl;
    @SerializedName("organIdRef")
    String organIdRef;
    @SerializedName("actions")
    Action actions;

    public Action getActions() {
        return actions;
    }

    public void setActions(Action actions) {
        this.actions = actions;
    }

    public class Action{
        @SerializedName("actionsLectures")
        String actionsLectures;

        public String getActionsLectures() {
            return actionsLectures;
        }

        public void setActionsLectures(String actionsLectures) {
            this.actionsLectures = actionsLectures;
        }
    }

    public Category(long createdDate, long updatedDate, boolean isDeleted, String categoryId, String categoryName, String categoryImageUrl, String organIdRef) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
        this.organIdRef = organIdRef;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public String getOrganIdRef() {
        return organIdRef;
    }

    public void setOrganIdRef(String organIdRef) {
        this.organIdRef = organIdRef;
    }
}
