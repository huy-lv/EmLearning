package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 07-May-17.
 */

public class LoginResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    User user;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }
}
