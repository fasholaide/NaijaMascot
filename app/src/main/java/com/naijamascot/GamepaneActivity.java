package com.naijamascot;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GamepaneActivity extends Activity {

    private Cursor naijaMascots;
    private NaijaMascotDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = (ListView)findViewById(R.id.listView);

        db = new NaijaMascotDatabase(this);
        naijaMascots = db.getMascots(); // you would not typically call this on the main thread
        List <String> firstNameArayList = new ArrayList<String>();
        if(naijaMascots.moveToFirst()) {
            Log.d("NaijaMascot", "I got here, I was able to get some data");
            while(naijaMascots.moveToNext()) {
                firstNameArayList.add(naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_FIRSTNAME)));
                Log.d("NAIJAMASCOT", naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_FIRSTNAME)));
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_example_layout, firstNameArayList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        naijaMascots.close();
        db.close();
    }
}
