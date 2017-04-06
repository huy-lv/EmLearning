package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Book;

import java.util.ArrayList;

/**
 * Created by huylv on 02-Apr-17.
 */

public class BookListResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    ArrayList<Book> data;

    public BookListResponse(int error, String message, ArrayList<Book> data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Book> getData() {
        return data;
    }

    public void setData(ArrayList<Book> data) {
        this.data = data;
    }
}
