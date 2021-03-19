package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class InsertLandmarkAsync extends AsyncTask<Landmark, Void, Void> {

    private Context context;

    public InsertLandmarkAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Landmark... landmarks) {
        DatabaseInstance database = DatabaseInstance.getInstance(context);
        for(int i = 0; i<landmarks.length; i++) {
            database.getDatabase().landmarkDAO().insertLandmark(landmarks[i]);
        }
        return null;
    }
}
