package com.hudati.emlearning.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.hudati.emlearning.R;
import com.hudati.emlearning.model.Book;
import com.hudati.emlearning.model.Category;

import java.util.ArrayList;

/**
 * Created by huylv on 21-Mar-17.
 */

public class Utils {
    public static ArrayList<Book> getBookList(Context c){
        String s = "IELTS CAMBRIDGE ";
        ArrayList<Book> b = new ArrayList<>();
        for(int i=0;i<11;i++) {
            int id = c.getResources().getIdentifier("i"+(i+1),"mipmap",c.getPackageName());
            b.add(new Book(s + (i+1) ,id ));
        }
        return b;
    }

    public static ArrayList<Category> getCategoryList() {
        ArrayList<Category> c = new ArrayList<>();
        c.add(new Category("Listening", R.mipmap.category1));
        c.add(new Category("Writing", R.mipmap.category1));
        c.add(new Category("Reading", R.mipmap.category1));
        c.add(new Category("Speaking", R.mipmap.category1));
        return c;
    }

    public static int calculateNumOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = Math.round(dpWidth / 120);
        return noOfColumns;
    }
}
