package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Api;

import java.util.ArrayList;

/**
 * Created by huylv on 03-Apr-17.
 */

public class RootApiResponse {
    @SerializedName("success")
    int success;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    APIList apiList;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public APIList getApiList() {
        return apiList;
    }

    public void setApiList(APIList apiList) {
        this.apiList = apiList;
    }

    public class APIList {
        @SerializedName("_api_lecture")
        String _api_lecture;
        @SerializedName("_api_practice_question")
        String _api_practice_question;
        @SerializedName("_api_practice")
        String _api_practice;
        @SerializedName("_api_root")
        String _api_root;
        @SerializedName("_api_user_login")
        String _api_user_login;
        @SerializedName("_api_home")
        String _api_home;
        @SerializedName("_api_books")
        String _api_books;
        @SerializedName("_api_practice_action")
        String _api_practice_action;
        @SerializedName("_api_user_logout")
        String _api_user_logout;
        @SerializedName("_api_practice_details")
        String _api_practice_details;
        @SerializedName("_api_home_category")
        String _api_home_category;
        @SerializedName("_api_home_banners")
        String _api_home_banners;
        @SerializedName("_api_home_books")
        String _api_home_books;

        public String get_api_lecture() {
            return _api_lecture;
        }

        public String get_api_practice_question() {
            return _api_practice_question;
        }

        public String get_api_practice() {
            return _api_practice;
        }

        public String get_api_root() {
            return _api_root;
        }

        public String get_api_user_login() {
            return _api_user_login;
        }

        public String get_api_home() {
            return _api_home;
        }

        public String get_api_books() {
            return _api_books;
        }

        public String get_api_practice_action() {
            return _api_practice_action;
        }

        public String get_api_user_logout() {
            return _api_user_logout;
        }

        public String get_api_practice_details() {
            return _api_practice_details;
        }

        public String get_api_home_category() {
            return _api_home_category;
        }

        public String get_api_home_banners() {
            return _api_home_banners;
        }

        public String get_api_home_books() {
            return _api_home_books;
        }
    }
}
