package com.example.proiectdam_serbansorinaalexandra.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam_serbansorinaalexandra.Data.Landmark;
import com.example.proiectdam_serbansorinaalexandra.Data.LandmarkAdapter;
import com.example.proiectdam_serbansorinaalexandra.Data.User;
import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;
import com.example.proiectdam_serbansorinaalexandra.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserLandmarksActivity extends AppCompatActivity {

    private ListView lvVisitedLandmarks;
    private List<Landmark> landmarks = new ArrayList<>();
    private DatabaseInstance database;
    private String username;
    private LandmarkAdapter adapter;
    private Button btnSaveToTxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landmarks);
        username = getIntent().getStringExtra("username");
        database = DatabaseInstance.getInstance(this);
        lvVisitedLandmarks = findViewById(R.id.lv_visited_landmarks);
        User currentU = database.getUserDAO().getUser(username);
        landmarks = database.getLandmarkDAO().selectedLandmarks(currentU.id);
        adapter = new LandmarkAdapter(this,R.layout.adapter_landmark, landmarks);
        lvVisitedLandmarks.setAdapter(adapter);
        btnSaveToTxt = findViewById(R.id.btn_save_to_txt);
        btnSaveToTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String space = "\n";
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = openFileOutput("Landmarks.txt", MODE_PRIVATE);
                    for(int i = 0; i < landmarks.size(); i++) {
                        fileOutputStream.write(landmarks.get(i).toString().getBytes());
                        fileOutputStream.write(space.getBytes());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Toast.makeText(UserLandmarksActivity.this, "Saved to .txt", Toast.LENGTH_LONG).show();
            }
        });
    }
}
