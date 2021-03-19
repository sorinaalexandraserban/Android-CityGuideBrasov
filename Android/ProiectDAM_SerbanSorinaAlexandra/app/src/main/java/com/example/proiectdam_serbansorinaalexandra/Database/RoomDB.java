package com.example.proiectdam_serbansorinaalexandra.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.proiectdam_serbansorinaalexandra.Data.Landmark;
import com.example.proiectdam_serbansorinaalexandra.Data.User;

@Database(entities = {User.class, Landmark.class}, version =1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public abstract UserDAO userDAO();
    public abstract LandmarkDAO landmarkDAO();
}
