package com.hudati.emlearning.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
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
    public static final String INTENT_KEY_START_LISTENING = "INTENT_KEY_START_LISTENING";

    public static final String FRAGMENT_KEY_PAGE = "FRAGMENT_KEY_PAGE";
    public static final String INTENT_KEY_START_READING = "INTENT_KEY_START_READING";
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

    public static Bitmap fastblur(Bitmap sentBitmap, float scale, int radius) {

        int width = Math.round(sentBitmap.getWidth() * scale);
        int height = Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

    public enum PracticeType{
        Listening,Speaking,Reading,Writing
    }
}
