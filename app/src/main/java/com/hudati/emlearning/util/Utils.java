package com.hudati.emlearning.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;

import com.hudati.emlearning.adapter.BookAdapter;
import com.hudati.emlearning.api.RootApiResponse;
import com.hudati.emlearning.model.Book;

import java.io.File;

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
    public static final String INTENT_KEY_PRACTICE_TITLE = "INTENT_KEY_PRACTICE_TITLE";
    public static final String INTENT_KEY_PRACTICE_ACTION = "INTENT_KEY_PRACTICE_ACTION";
    public static final String INTENT_KEY_START_LISTENING = "INTENT_KEY_START_LISTENING";

    public static final String FRAGMENT_KEY_PAGE = "FRAGMENT_KEY_PAGE";
    public static final String INTENT_KEY_START_READING = "INTENT_KEY_START_READING";
    public static final String INTENT_KEY_START_LISTENING_MP3 = "INTENT_KEY_START_LISTENING_MP3";

    public static final String SP_CHECK_USER = "SP_CHECK_USER";
    public static final String SP_ACCESS_TOKEN = "SP_ACCESS_TOKEN";
    public static final String INTENT_KEY_BOOK_MP3 = "INTENT_KEY_BOOK_MP3";
    public static final String INTENT_KEY_PRACTICE_SUBTITLE = "INTENT_KEY_PRACTICE_SUBTITLE";
    public static String ACCESS_TOKEN;
    public static boolean LOGGED_IN;

    public static RootApiResponse.APIList apiList;

    public static String YOUTUBE_DEVELOPER_KEY = "AIzaSyAQjlRkYGkV2X7pmxOufK_7XR9afuW44hI";
    public static File[] bookDownloaded;

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap blurRenderScript(Context context, Bitmap smallBitmap, int radius) {
        try {
            smallBitmap = RGB565toARGB888(smallBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Bitmap bitmap = Bitmap.createBitmap(
                smallBitmap.getWidth(), smallBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(context);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, smallBitmap);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(radius); // radius must be 0 < r <= 25
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();

        return bitmap;

    }

    private static Bitmap RGB565toARGB888(Bitmap img) throws Exception {
        int numPixels = img.getWidth() * img.getHeight();
        int[] pixels = new int[numPixels];

        //Get JPEG pixels.  Each int is the color values for one pixel.
        img.getPixels(pixels, 0, img.getWidth(), 0, 0, img.getWidth(), img.getHeight());

        //Create a Bitmap of the appropriate format.
        Bitmap result = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);

        //Set RGB pixels.
        result.setPixels(pixels, 0, result.getWidth(), 0, 0, result.getWidth(), result.getHeight());
        return result;
    }

    public static boolean isNetworkConnected(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static void scanBookListInStorage() {
        File folder = new File(Environment.getExternalStorageDirectory() + "/emlearning");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
            Log.e("cxz", "folder existed");
        }
        if (success) {
            Log.e("cxz", "folder created");
            Utils.bookDownloaded = folder.listFiles();
        }
    }

    public static void notifyBookList(BookAdapter bookAdapter) {
        if (bookDownloaded.length > 0) {
            for (File f : bookDownloaded) {
                for (Book b : bookAdapter.objectList) {
                    if (f.getName().equals(b.getBookName())) {
                        b.setDownloaded(true);
                        b.setFilePath(f.getPath());
                    }
                }
                Log.e("cxz", "file name " + f.getName());
            }
            bookAdapter.notifyDataSetChanged();
        } else {
            Log.e("cxz", "no file in folder book list");
        }
    }

    public enum PracticeType{
        Listening,Speaking,Reading,Writing
    }
}
