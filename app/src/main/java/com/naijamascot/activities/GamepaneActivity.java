package com.naijamascot.activities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.naijamascot.R;
import com.naijamascot.databases.NaijaMascotContract;
import com.naijamascot.databases.NaijaMascotDatabase;
import com.naijamascot.util.NaijaMascotUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GamepaneActivity extends Activity {

    private final String NAIJAMASCOT_UI_THREAD = "NAIJAMASCOT_UI_THREAD";
    private Cursor naijaMascots;
    private final int NUMBER_OF_MASCOTS = 50;
    private int count = 0;
    private List<Integer> NaijaMascotShuffle = new NaijaMascotUtil(NUMBER_OF_MASCOTS).getShuffleArray();
    private byte[] naijaMascotImage;
    private String firstName, lastName, hint, answer;
    private ImageView mascotImageView;
    private TextView hintTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamepane_activity);

        LoadNaijaMascotFromDataBase loadNaijaMascotFromDataBase = new LoadNaijaMascotFromDataBase();
        try {
            naijaMascots = loadNaijaMascotFromDataBase.execute(this).get();
        } catch (InterruptedException ex) {
            Log.d(NAIJAMASCOT_UI_THREAD, "InterruptedException" + " " + ex.getMessage());
            System.exit(1);
        } catch (ExecutionException ex) {
            Log.d(NAIJAMASCOT_UI_THREAD, "ExecutionException" + " " + ex.getMessage());
            System.exit(1);
        }
        renderPaneForPlay(count);

        //Set the Action That happens When the Next Button isPressed
        Button button = (Button) findViewById(R.id.nextMascot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                renderPaneForPlay(count);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        naijaMascots.close();
    }

    private void renderPaneForPlay(int count) {
        if (naijaMascots.moveToFirst()) {
            naijaMascots.moveToPosition(NaijaMascotShuffle.get(count) - 1);
            naijaMascotImage = naijaMascots.getBlob(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_IMAGE));
            firstName = naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_FIRSTNAME));
            lastName = naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_LASTNAME));
            answer = naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_ANSWER));
            hint = naijaMascots.getString(naijaMascots.getColumnIndex(NaijaMascotContract.NaijaMascotColumns.NAIJAMASCOT_HINT));
        }
        //Set the Image in place
        InputStream inputStream = new ByteArrayInputStream(naijaMascotImage);
        mascotImageView = (ImageView) findViewById(R.id.mascotImageView);
        mascotImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mascotImageView.setAdjustViewBounds(true);
        mascotImageView.setImageBitmap(BitmapFactory.decodeStream(inputStream));
        //Set the Hint Value in place
        hintTextView = (TextView) findViewById(R.id.mascotHintText);
        hintTextView.setText(hint);
    }


    private class LoadNaijaMascotFromDataBase extends AsyncTask<Context, Integer, Cursor> {
        @Override
        protected Cursor doInBackground(Context... context) {
            NaijaMascotDatabase db = new NaijaMascotDatabase(context[0]);
            Cursor cursor = db.getMascots();
            return cursor;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        @Override
        protected void onPostExecute(Cursor result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }
}
