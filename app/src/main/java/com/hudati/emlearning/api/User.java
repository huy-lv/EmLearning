package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.util.Utils;

/**
 * Created by huylv on 07-May-17.
 */

public class User {
    @SerializedName("accessToken")
    String accessToken;
    @SerializedName("user")
    UserInfo userInfo;

    public String getAccessToken() {
        return accessToken;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public class UserInfo {
        @SerializedName("createdDate")
        long createdDate;
        @SerializedName("updatedDate")
        long updatedDate;
        @SerializedName("userId")
        String userId;
        @SerializedName("userName")
        String userName;
        @SerializedName("userEmail")
        String userEmail;
        @SerializedName("userPassRand")
        String userPassRand;
        @SerializedName("userPassHash")
        String userPassHash;
        @SerializedName("userPhone")
        String userPhone;
        @SerializedName("userFacebookId")
        String userFacebookId;
        @SerializedName("userGooglePlusId")
        String userGooglePlusId;
        @SerializedName("userAvatar")
        String userAvatar;
        @SerializedName("userFullName")
        String userFullName;
        @SerializedName("userRole")
        Utils.UserRole userRole;
        @SerializedName("organIdRef")
        String organIdRef;

        public long getCreatedDate() {
            return createdDate;
        }

        public long getUpdatedDate() {
            return updatedDate;
        }

        public String getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public String getUserPassRand() {
            return userPassRand;
        }

        public String getUserPassHash() {
            return userPassHash;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public String getUserFacebookId() {
            return userFacebookId;
        }

        public String getUserGooglePlusId() {
            return userGooglePlusId;
        }

        public String getUserAvatar() {
            return userAvatar;
        }

        public String getUserFullName() {
            return userFullName;
        }

        public Utils.UserRole getUserRole() {
            return userRole;
        }

        public String getOrganIdRef() {
            return organIdRef;
        }
    }
}
