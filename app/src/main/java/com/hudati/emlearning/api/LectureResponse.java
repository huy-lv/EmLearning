package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Lecture;

import java.util.ArrayList;

/**
 * Created by huylv on 04-Apr-17.
 */

public class LectureResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<Header> data;

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Header> getData() {
        return data;
    }

    public class Header{
        @SerializedName("header")
        GroupLectures header;

        public GroupLectures getHeader() {
            return header;
        }
    }

    public class GroupLectures {
        long id;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @SerializedName("createdDate")
        String createdDate;
        @SerializedName("updatedDate")
        String updatedDate;
        @SerializedName("headerId")
        String headerId;
        @SerializedName("headerName")
        String headerName;
        @SerializedName("lectures")
        ArrayList<Lecture> lectures;

        public String getCreatedDate() {
            return createdDate;
        }

        public String getUpdatedDate() {
            return updatedDate;
        }

        public String getHeaderId() {
            return headerId;
        }

        public String getHeaderName() {
            return headerName;
        }

        public ArrayList<Lecture> getLectures() {
            return lectures;
        }
    }
}
