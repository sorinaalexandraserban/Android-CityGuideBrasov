package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

import java.util.List;

public class GetLandmarksAsync extends AsyncTask<String, Void, List<Landmark>> {

    private Context context;

    public GetLandmarksAsync(Context context) {
        this.context = context;
    }

    @Override
    protected List<Landmark> doInBackground(String... strings) {
        return DatabaseInstance.getInstance(context).getDatabase().landmarkDAO().getAllLandmarks();
    }
}
