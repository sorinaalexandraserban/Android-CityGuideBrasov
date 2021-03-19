package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class InsertUserAsync extends AsyncTask<User,Void,Void> {

    private Context context;

    public InsertUserAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(User... users) {

        DatabaseInstance db = DatabaseInstance.getInstance(context);
        for(int i = 0; i < users.length; i++) {
            db.getDatabase().userDAO().insertUser(users[i]);
        }
        return null;
    }
}
