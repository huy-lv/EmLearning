package com.hudati.emlearning.util;

import android.content.Context;

import com.hudati.emlearning.model.Book;

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
}
