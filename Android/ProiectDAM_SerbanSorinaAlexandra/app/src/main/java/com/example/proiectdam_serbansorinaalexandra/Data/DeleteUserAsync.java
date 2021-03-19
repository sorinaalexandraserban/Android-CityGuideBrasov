package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class DeleteUserAsync extends AsyncTask<User,Void,Void> {

    private Context context;

    public DeleteUserAsync(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(User... users) {
        DatabaseInstance database = DatabaseInstance.getInstance(context);
        if(users.length > 0) {
            database.getDatabase().userDAO().deleteUser(users[0]);
        }
        return null;
    }
}
