package com.example.proiectdam_serbansorinaalexandra.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.proiectdam_serbansorinaalexandra.Data.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    public void insertUser(User user);

    @Delete
    public void deleteUser(User user);

    @Update
    public void updateUser(User user);

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    public int checkUsername(String username);

    @Query("SELECT * FROM users WHERE username = :username")
    public User getUser(String username);

    @Query("SELECT COUNT(*) FROM users WHERE username = :searchedUsername and password = :searchedPassword")
    public int userExists(String searchedUsername, String searchedPassword);

    @Query("SELECT * FROM users")
    public List<User> getAllUsers();
}

