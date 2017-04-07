package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Section;

import java.util.ArrayList;

/**
 * Created by huylv on 06-Apr-17.
 */

public class SectionResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<Section> sections;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }
}
