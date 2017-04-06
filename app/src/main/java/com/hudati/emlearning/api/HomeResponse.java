package com.hudati.emlearning.api;

import com.google.gson.annotations.SerializedName;
import com.hudati.emlearning.model.Banner;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.model.Category;
import com.hudati.emlearning.model.Lecture;
import com.hudati.emlearning.model.Practice;

import java.util.ArrayList;

/**
 * Created by huylv on 03-Apr-17.
 */

public class HomeResponse {
    @SerializedName("error")
    int error;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    HomeSection data;

    public HomeResponse(int error, String message, HomeSection data) {
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

    public HomeSection getData() {
        return data;
    }

    public void setData(HomeSection data) {
        this.data = data;
    }

    public class HomeSection {
        @SerializedName("banners")
        ArrayList<Banner> banners;
        @SerializedName("categories")
        ArrayList<Category> categories;
        @SerializedName("lectures")
        ArrayList<Lecture> lectures;
        @SerializedName("books")
        ArrayList<Book> books;

        public ArrayList<Practice> getPractices() {
            return practices;
        }

        @SerializedName("practices")
        ArrayList<Practice> practices;

        public HomeSection(ArrayList<Banner> banners, ArrayList<Category> categories, ArrayList<Lecture> lectures, ArrayList<Book> books) {
            this.banners = banners;
            this.categories = categories;
            this.lectures = lectures;
            this.books = books;
        }

        public ArrayList<Banner> getBanners() {
            return banners;
        }

        public void setBanners(ArrayList<Banner> banners) {
            this.banners = banners;
        }

        public ArrayList<Category> getCategories() {
            return categories;
        }

        public void setCategories(ArrayList<Category> categories) {
            this.categories = categories;
        }

        public ArrayList<Lecture> getLectures() {
            return lectures;
        }

        public void setLectures(ArrayList<Lecture> lectures) {
            this.lectures = lectures;
        }

        public ArrayList<Book> getBooks() {
            return books;
        }

        public void setBooks(ArrayList<Book> books) {
            this.books = books;
        }
    }

}
