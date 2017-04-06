package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 03-Apr-17.
 */

public class Banner {
    @SerializedName("createdDate")
    long createdDate;
    @SerializedName("updatedDate")
    long updatedDate;
    @SerializedName("isDeleted")
    boolean isDeleted;
    @SerializedName("bannerId")
    String bannerId;
    @SerializedName("banerTitle")
    String banerTitle;
    @SerializedName("bannerSubTitle")
    String bannerSubTitle;
    @SerializedName("bannerImageUrl")
    String bannerImageUrl;
    @SerializedName("bannerHref")
    String bannerHref;
    @SerializedName("organIdRef")
    String organIdRef;

    public Banner(long createdDate, long updatedDate, boolean isDeleted, String bannerId, String banerTitle, String bannerSubTitle, String bannerImageUrl, String bannerHref, String organIdRef) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isDeleted = isDeleted;
        this.bannerId = bannerId;
        this.banerTitle = banerTitle;
        this.bannerSubTitle = bannerSubTitle;
        this.bannerImageUrl = bannerImageUrl;
        this.bannerHref = bannerHref;
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

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBanerTitle() {
        return banerTitle;
    }

    public void setBanerTitle(String banerTitle) {
        this.banerTitle = banerTitle;
    }

    public String getBannerSubTitle() {
        return bannerSubTitle;
    }

    public void setBannerSubTitle(String bannerSubTitle) {
        this.bannerSubTitle = bannerSubTitle;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBannerHref() {
        return bannerHref;
    }

    public void setBannerHref(String bannerHref) {
        this.bannerHref = bannerHref;
    }

    public String getOrganIdRef() {
        return organIdRef;
    }

    public void setOrganIdRef(String organIdRef) {
        this.organIdRef = organIdRef;
    }
}
