package com.example.proiectdam_serbansorinaalexandra.Data;


import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

import java.util.List;

public class GetUserAsync extends AsyncTask<String, Void, List<User>> {

    private Context context;

    public GetUserAsync(Context context) {
        this.context = context;
    }

    @Override
    protected List<User> doInBackground(String... strings) {
        return DatabaseInstance.getInstance(context).getDatabase().userDAO().getAllUsers();
    }
}
