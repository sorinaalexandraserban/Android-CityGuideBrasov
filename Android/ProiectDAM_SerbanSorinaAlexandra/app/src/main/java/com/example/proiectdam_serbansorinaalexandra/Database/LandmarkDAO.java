package com.example.proiectdam_serbansorinaalexandra.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectdam_serbansorinaalexandra.Data.Landmark;

import java.util.List;

@Dao
public interface LandmarkDAO {

    @Insert
    public void insertLandmark(Landmark landmark);

    @Update
    public void updateLandmark(Landmark landmark);

    @Delete
    public void deleteLandmark(Landmark landmark);

    @Query("SELECT COUNT(*) FROM landmarks WHERE id = :id")
    public int checkID(int id);

    @Query("SELECT * FROM landmarks WHERE userID=:userID")
    public List<Landmark> selectedLandmarks(int userID);

    @Query("SELECT * FROM landmarks")
    public List<Landmark> getAllLandmarks();


}
