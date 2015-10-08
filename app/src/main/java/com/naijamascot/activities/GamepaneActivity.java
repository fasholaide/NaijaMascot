package com.naijamascot.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.naijamascot.R;
import com.naijamascot.databases.NaijaMascotContract;
import com.naijamascot.databases.NaijaMascotDatabase;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class GamepaneActivity extends Activity {

    private Cursor naijaMascots;
    private NaijaMascotDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_example_layout);
        ImageView imageView;

        db = new NaijaMascotDatabase(this);
        naijaMascots = db.getMascots(); // you would not typically call this on the main thread
        List <byte[]> mascotsArayList = new ArrayList<byte[]>();
        if(naijaMascots.moveToFirst()) {
            Log.d("NaijaMascot", "I got here, I was able to get some data");
//            while(naijaMascots.moveToNext()) {
//                mascotsArayList.add(naijaMascots.getBlob(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_IMAGE)));
//                Log.d("NAIJAMASCOT", naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_FIRSTNAME)));
//            }
            InputStream inputStream = new ByteArrayInputStream((naijaMascots.getBlob(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_IMAGE))));
            imageView = (ImageView)findViewById(R.id.mascot);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setAdjustViewBounds(true);
            imageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));
//        ArrayAdapter<byte[]> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_example_layout, R.id.mascot, mascotsArayList);
//        setListAdapter(arrayAdapter);
    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        naijaMascots.close();
        db.close();
    }
}
