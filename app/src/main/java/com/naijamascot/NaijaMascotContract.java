package com.naijamascot;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by bfashola on 9/22/15.
 */
public class NaijaMascotContract {
    interface NaijaMascotColumns{
        String NAIJAMASCOT_FIRSTNAME = "Firstname";
        String NAIJAMASCOT_LASTNAME = "Lastname";
        String NAIJAMASCOT_HINT = "Hint";
        String NAIJAMASCOT_ANSWER = "Answer";
        String NAIJAMASCOT_CATEGORY = "Category";
        String NAIJAMASCOT_IMAGE = "Image";
    }

    public static final String CONTENT_AUTHORITY = "com.naijamascot.provider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_NAIJAMASCOT  = "NaijaMascots";

    public static final String [] TOP_LEVEL_PATHS = {
            PATH_NAIJAMASCOT
    };

    private static class NaijaMascts implements NaijaMascotColumns, BaseColumns {
        public static final Uri URI_CONTENT =
                BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_NAIJAMASCOT).build();

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.naijamacsot" + PATH_NAIJAMASCOT;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.naijamacsot" + PATH_NAIJAMASCOT;

        public static Uri buildNaijaMascotUri(String Id){
            return URI_CONTENT.buildUpon().appendEncodedPath(Id).build();
        }
        public static String getNaijaMascotId(Uri uri){
            return uri.getPathSegments().get(1);
        }

    }
}
