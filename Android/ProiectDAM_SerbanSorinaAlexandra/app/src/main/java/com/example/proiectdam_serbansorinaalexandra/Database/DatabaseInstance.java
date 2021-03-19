package com.example.proiectdam_serbansorinaalexandra.Database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseInstance {

    private static DatabaseInstance instance;
    private RoomDB database;

    private DatabaseInstance(Context context) {
        database = Room.databaseBuilder(context, RoomDB.class, "city_database").allowMainThreadQueries().build();
    }

    public static DatabaseInstance getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseInstance(context);
        }
        return instance;
    }

    public RoomDB getDatabase() { return database; }

    public UserDAO getUserDAO() { return database.userDAO(); }

    public LandmarkDAO getLandmarkDAO() { return database.landmarkDAO(); }
}
