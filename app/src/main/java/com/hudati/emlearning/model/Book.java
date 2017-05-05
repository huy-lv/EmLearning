package com.hudati.emlearning.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by huylv on 21-Mar-17.
 */

public class Book {
    @SerializedName("isDeleted")
    boolean isDeleted;
    @SerializedName("bookId")
    String bookId;
    @SerializedName("bookName")
    String bookName;
    @SerializedName("bookDescription")
    String bookDescription;
    @SerializedName("bookUrl")
    String bookUrl;
    @SerializedName("bookAudio")
    String bookAudio;
    @SerializedName("bookImageUrl")
    String bookImageUrl;
    @SerializedName("organIdRef")
    String organIdRef;

    String filePath;
    boolean isDownloaded;

    public Book(boolean isDeleted, String bookId, String bookName, String bookDescription, String bookUrl, String bookAudio, String bookImageUrl, String organIdRef) {
        this.isDeleted = isDeleted;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookDescription = bookDescription;
        this.bookUrl = bookUrl;
        this.bookAudio = bookAudio;
        this.bookImageUrl = bookImageUrl;
        this.organIdRef = organIdRef;
    }

    public Book(String bookName, String filePath) {
        this.bookName = bookName;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getBookAudio() {
        return bookAudio;
    }

    public void setBookAudio(String bookAudio) {
        this.bookAudio = bookAudio;
    }

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getOrganIdRef() {
        return organIdRef;
    }

    public void setOrganIdRef(String organIdRef) {
        this.organIdRef = organIdRef;
    }
}
