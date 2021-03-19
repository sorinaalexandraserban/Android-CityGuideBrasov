package com.example.proiectdam_serbansorinaalexandra.Data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;

public class GetUserLoginAsync extends AsyncTask<User,Void,User> {

    private Context context;

    public GetUserLoginAsync(Context context) {
        this.context = context;
    }

    @Override
    protected User doInBackground(User... users) {
        String username = users[0].getUsername();
        return DatabaseInstance.getInstance(context).getDatabase().userDAO().getUser(username);
    }
}
