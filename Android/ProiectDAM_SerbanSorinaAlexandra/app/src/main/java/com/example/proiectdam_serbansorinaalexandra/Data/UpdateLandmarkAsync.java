package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class UpdateLandmarkAsync extends AsyncTask<Landmark,Void,Void> {

    private Context context;

    public UpdateLandmarkAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Landmark... landmarks) {
        DatabaseInstance databaseInstance = DatabaseInstance.getInstance(context);
        databaseInstance.getDatabase().landmarkDAO().updateLandmark(landmarks[0]);
        return null;
    }
}
