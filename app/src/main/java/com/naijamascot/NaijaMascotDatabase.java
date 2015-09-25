package com.naijamascot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bfashola on 9/22/15.
 */
public class NaijaMascotDatabase extends SQLiteOpenHelper {
    private static final String TAG = NaijaMascotDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "naijamascotdb";
    private static final int DATABASE_VERSION = 2;
    private final Context mContext;

    public NaijaMascotDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    interface Table {
        String NAIJAMASCOTS = "NaijaMascots";
    }
}
