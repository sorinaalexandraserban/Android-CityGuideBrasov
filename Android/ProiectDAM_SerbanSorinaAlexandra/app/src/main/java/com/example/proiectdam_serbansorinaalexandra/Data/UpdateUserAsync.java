package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class UpdateUserAsync extends AsyncTask<User,Void,Void> {

    private Context context;

    public UpdateUserAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(User... users) {
        DatabaseInstance database = DatabaseInstance.getInstance(context);
        database.getDatabase().userDAO().updateUser(users[0]);
        return null;
    }
}
