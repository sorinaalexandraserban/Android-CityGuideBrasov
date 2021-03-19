package com.example.proiectdam_serbansorinaalexandra.Data;

import android.os.Parcel;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "landmarks", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userID", onDelete = ForeignKey.CASCADE))
public class Landmark implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userID;

    private String name;
    private String location;
    private String type;
    private String rating;

    public Landmark() {

    }

    public Landmark(String name, String location, String type, String rating) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.rating = rating;
    }

    protected Landmark(Parcel in) {
        name = in.readString();
        location = in.readString();
        type = in.readString();
        rating = in.readString();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getRating() { return rating; }

    public void setRating(String rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Landmark{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
