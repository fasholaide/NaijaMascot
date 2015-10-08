package com.naijamascot.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by bfashola on 9/22/15.
 */
public class NaijaMascotDatabase extends SQLiteAssetHelper {
    private static final String TAG = NaijaMascotDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "naijamascotdb";
    private static final int DATABASE_VERSION = 1;

    public NaijaMascotDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getMascots(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        String sqlSelect [] = {
                NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_FIRSTNAME,
                NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_LASTNAME,
                NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_HINT,
                NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_ANSWER,
                NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_IMAGE,
                NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_CATEGORY
        };
        String sqlTable = Table.NAIJAMASCOTS;
        queryBuilder.setTables(sqlTable);

        Cursor mCursor = queryBuilder.query(db, sqlSelect, null, null, null, null, null);
        mCursor.moveToFirst();
        return mCursor;
    }
    interface Table {
        String NAIJAMASCOTS = "NaijaMascots";
    }
}