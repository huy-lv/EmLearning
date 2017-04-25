package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 17-Apr-17.
 */

public class AudioGoogle {
    @SerializedName("kind")
    String kind;
    @SerializedName("id")
    String id;
    @SerializedName("webContentLink")
    String webContentLink;

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String getWebContentLink() {
        return webContentLink;
    }
}
