package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 07-May-17.
 */

public class BaseResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    String data;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
