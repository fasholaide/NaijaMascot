package com.naijamascot;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;

public class gamepane_activity extends ListActivity {

    private Cursor naijaMascots;
    private NaijaMascotDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new NaijaMascotDatabase(this);
        naijaMascots = db.getMascots(); // you would not typically call this on the main thread

        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                R.id.listView,
                naijaMascots,
                new String[] {NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_FIRSTNAME},
                new int[] {R.id.textName});

        getListView().setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        naijaMascots.close();
        db.close();
    }
}
