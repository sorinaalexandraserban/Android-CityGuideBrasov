package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class DeleteLandmarkAsync extends AsyncTask<Landmark,Void,Void> {

    private Context context;

    public DeleteLandmarkAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Landmark... landmarks) {
        DatabaseInstance databaseInstance = DatabaseInstance.getInstance(context);
        if(landmarks.length > 0) {
            databaseInstance.getDatabase().landmarkDAO().deleteLandmark(landmarks[0]);
        }
        return null;
    }
}
