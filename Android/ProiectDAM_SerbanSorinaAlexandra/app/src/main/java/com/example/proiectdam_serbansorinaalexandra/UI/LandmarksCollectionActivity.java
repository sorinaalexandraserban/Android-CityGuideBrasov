package com.example.proiectdam_serbansorinaalexandra.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam_serbansorinaalexandra.Data.JSONReader;
import com.example.proiectdam_serbansorinaalexandra.Data.Landmark;
import com.example.proiectdam_serbansorinaalexandra.Data.LandmarkAdapter;
import com.example.proiectdam_serbansorinaalexandra.Data.User;
import com.example.proiectdam_serbansorinaalexandra.Database.SharedPrefs;
import com.example.proiectdam_serbansorinaalexandra.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LandmarksCollectionActivity extends AppCompatActivity {

    private ListView lvLandmarks;
    private List<Landmark> landmarks = new ArrayList<>();
    private LandmarkAdapter adapter;

    public LandmarksCollectionActivity() { }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landmarks_collection);
        lvLandmarks = findViewById(R.id.lv_landmarks);
        adapter = new LandmarkAdapter(LandmarksCollectionActivity.this, R.layout.adapter_landmark, landmarks);
        lvLandmarks.setAdapter(adapter);
        connectJson();
    }

    private void connectJson() {
        JSONReader jsonReader = new JSONReader() {
            @Override
            protected void onPostExecute(String s) {
                if(s != null) {
                    List<Landmark> landmarks = parceLandmarksJSON(s);
                    adapter.updateListLandmarks(landmarks);
                }
            }
        };
        try {
            jsonReader.execute(new URL("https://run.mocky.io/v3/09915e8d-323a-4aaa-b5f1-557d5f798ad3"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
