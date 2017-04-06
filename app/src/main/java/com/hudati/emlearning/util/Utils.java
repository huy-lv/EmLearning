package com.hudati.emlearning.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.util.Log;

import com.hudati.emlearning.api.RootApiResponse;

/**
 * Created by huylv on 21-Mar-17.
 */

public class Utils {
    public static final String INTENT_KEY_BOOK_URL = "INTENT_KEY_BOOK_URL";
    public static final String INTENT_KEY_ACTION_LECTURE = "INTENT_KEY_ACTION_LECTURE";
    public static final String INTENT_KEY_ACTION_LECTURE_PAGE = "INTENT_KEY_ACTION_LECTURE_PAGE";
    public static final String INTENT_KEY_ACTION_LECTURE_NAME = "INTENT_KEY_ACTION_LECTURE_NAME";
    public static final String INTENT_KEY_CATEGORY_NAME = "INTENT_KEY_CATEGORY_NAME";
    public static final String INTENT_ACTION_LECTURE_YOUTUBE = "INTENT_ACTION_LECTURE_YOUTUBE";
    public static final String INTENT_KEY_BOOK_NAME = "INTENT_KEY_BOOK_NAME";
    public static final String INTENT_KEY_PRACTICE_NAME = "INTENT_KEY_PRACTICE_NAME";
    public static final String INTENT_KEY_PRACTICE_ACTION = "INTENT_KEY_PRACTICE_ACTION";
    public static RootApiResponse.APIList apiList;

    public static String YOUTUBE_DEVELOPER_KEY = "AIzaSyAQjlRkYGkV2X7pmxOufK_7XR9afuW44hI";
//    public static ArrayList<Category> getCategoryList() {
//        ArrayList<Category> c = new ArrayList<>();
//        c.add(new Category("Listening", R.mipmap.category1));
//        c.add(new Category("Writing", R.mipmap.category1));
//        c.add(new Category("Reading", R.mipmap.category1));
//        c.add(new Category("Speaking", R.mipmap.category1));
//        return c;
//    }

    public static int calculateNumOfColumns(Context context,int colWidth) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
//        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = Math.round(displayMetrics.widthPixels / colWidth);
        Log.e("cxz","num of col = "+noOfColumns + " "+displayMetrics.widthPixels+ " "+colWidth);
        return noOfColumns;
    }

    public static void showInfoDialog(Context c,String message){
        (new AlertDialog.Builder(c)).setMessage(message).setPositiveButton("OK",null).create().show();
    }

    public static void showConfirmDialog(Context c, String message, DialogInterface.OnClickListener onClickYes){
        (new AlertDialog.Builder(c)).setMessage(message).setPositiveButton("YES",onClickYes).setNegativeButton("NO",null).create().show();
    }

    public enum PracticeType{
        Listening,Speaking,Reading,Writing
    }
}
