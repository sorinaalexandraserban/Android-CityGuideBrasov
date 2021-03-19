package com.example.proiectdam_serbansorinaalexandra.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam_serbansorinaalexandra.Data.Landmark;
import com.example.proiectdam_serbansorinaalexandra.Data.User;
import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;
import com.example.proiectdam_serbansorinaalexandra.R;

import java.util.ArrayList;
import java.util.List;

public class AddLandmarkActivity extends AppCompatActivity {

    private String username;
    private EditText etNameLandmark;
    private EditText etLocationLandmark;
    private Spinner spTypeLandmark;
    private RatingBar rbRatingLandmark;
    private Button btnSaveLandmark;

    DatabaseInstance database;

    List<Landmark> listLandmarks = new ArrayList<>();

    Intent intent = new Intent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_landmark);
        database = DatabaseInstance.getInstance(this);
        initViews();
        activateListeners();

        Landmark landmark = (Landmark)getIntent().getSerializableExtra("Obj");
        if(landmark != null) {
            int position = getIntent().getIntExtra("position", 0);
            etNameLandmark.setText(landmark.getName());
            etLocationLandmark.setText(landmark.getLocation());
            spTypeLandmark.setSelection(getIndex(spTypeLandmark, landmark.getType()));
            rbRatingLandmark.setRating(Float.parseFloat(landmark.getRating()));

            intent.putExtra("position", position);
        }
    }

    private void initViews() {
        username = getIntent().getStringExtra("username");
        etNameLandmark = findViewById(R.id.et_name_landmark);
        etLocationLandmark = findViewById(R.id.et_location);
        spTypeLandmark = findViewById(R.id.sp_type_landmark);
        rbRatingLandmark = findViewById(R.id.rb_rating_landmark);
        btnSaveLandmark = findViewById(R.id.btn_save_landmark);
    }

    private void activateListeners() {
        btnSaveLandmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewLandmark();
            }
        });
    }

    private void createNewLandmark() {
        if(validateIntroduceLandmark()) {
            String nameLandmark = etNameLandmark.getText().toString();
            String locationLandmark = etLocationLandmark.getText().toString();
            String typeLandmark = String.valueOf(spTypeLandmark.getSelectedItem());
            String ratingLandmark = String.valueOf(rbRatingLandmark.getRating());

            Landmark landmark = new Landmark();
            landmark.setName(nameLandmark);
            landmark.setLocation(locationLandmark);
            landmark.setType(typeLandmark);
            landmark.setRating(ratingLandmark);

            User currentUser = database.getUserDAO().getUser(username);
            landmark.userID = currentUser.id;
            database.getLandmarkDAO().insertLandmark(landmark);

            intent.putExtra("Object",landmark);
            setResult(RESULT_OK, intent);
            finish();
            Toast.makeText(AddLandmarkActivity.this, landmark.getName() + "was added successfully!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateIntroduceLandmark() {
        if(etNameLandmark.getText().toString().isEmpty()) {
            etNameLandmark.setError("Empty field! Please introduce the name of the landmark.");
            return false;
        }
        if(etLocationLandmark.getText().toString().isEmpty()) {
            etLocationLandmark.setError("Empty field! Please introduce the location of the landmark.");
            return false;
        }
        return true;
    }

    private int getIndex(Spinner spinner, String string) {
        for(int i = 0; i < spinner.getCount(); i++) {
            if(spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
                return i;
            }
        }
        return 0;
    }
}
