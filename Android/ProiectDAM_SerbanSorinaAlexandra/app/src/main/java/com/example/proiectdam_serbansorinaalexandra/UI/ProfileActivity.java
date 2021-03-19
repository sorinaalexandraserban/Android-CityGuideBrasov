package com.example.proiectdam_serbansorinaalexandra.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam_serbansorinaalexandra.Data.Landmark;
import com.example.proiectdam_serbansorinaalexandra.Data.User;
import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;
import com.example.proiectdam_serbansorinaalexandra.MainActivity;
import com.example.proiectdam_serbansorinaalexandra.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private String username;
    private TextView tv_welcome;
    private Button btnSignOut;
    private Button btnDeleteAccount;
    private Button btnChangePassword;
    private EditText et_newPassword;
    private Button btnConfirmPassword;
    private Button btnAddLandmark;
    private Button btnSavedLandmarks;
    private Button btnViewLandmarks;

    public static final Integer ADD_LANDMARK_REQUEST_CODE = 100;

    private DatabaseInstance database;

    List<Landmark> listLandmarks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        database = DatabaseInstance.getInstance(this);
        initViews();
        activateListeners();
    }

    private void initViews() {
        username = getIntent().getStringExtra("username");
        tv_welcome = findViewById(R.id.tv_welcome);
        tv_welcome.setText(tv_welcome.getText()+ " " + username);
        btnViewLandmarks = findViewById(R.id.btn_view_landmarks);
        btnSavedLandmarks = findViewById(R.id.btn_saved_landmarks);
        btnAddLandmark = findViewById(R.id.btn_add_landmark);
        btnSignOut = findViewById(R.id.btn_sign_out);
        btnDeleteAccount = findViewById(R.id.btn_delete_account);
        btnChangePassword = findViewById(R.id.btn_change_password);
        et_newPassword = findViewById(R.id.et_new_password);
        btnConfirmPassword = findViewById(R.id.btn_confirm_password);
    }

    public void activateListeners() {
        btnViewLandmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startViewAllLandmarks();
            }
        });

        btnSavedLandmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UserLandmarksActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnAddLandmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddLandmark();
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User currentUser = database.getUserDAO().getUser(username);
                if(currentUser != null) {
                    database.getUserDAO().deleteUser(currentUser);
                    Toast.makeText(ProfileActivity.this, "Account deleted!", Toast.LENGTH_LONG).show();
                }
                goHome();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_newPassword.setVisibility(View.VISIBLE);
                btnConfirmPassword.setVisibility(View.VISIBLE);
            }
        });

        btnConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User currentUser = database.getUserDAO().getUser(username);
                if(currentUser != null) {
                    if(et_newPassword.getText().toString().length() > 6) {
                        currentUser.password = et_newPassword.getText().toString();
                        database.getUserDAO().updateUser(currentUser);
                    } else {
                       et_newPassword.setError("Password must be at least 6 characters!");
                    }
                }
                Toast.makeText(ProfileActivity.this, "Password changed. Please SIGN IN again.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    public void startViewAllLandmarks() {
//        Intent intent = new Intent(ProfileActivity.this, LandmarksCollectionActivity.class);
//        intent.putParcelableArrayListExtra("List", (ArrayList)listLandmarks);
//        startActivity(intent);
          Intent intent = new Intent(this, LandmarksCollectionActivity.class);
        intent.putParcelableArrayListExtra("List", (ArrayList)listLandmarks);
          startActivity(intent);
          Toast.makeText(ProfileActivity.this, "Loading landmarks from JSON...", Toast.LENGTH_LONG).show();
    }

    public void startAddLandmark() {
        Intent intent = new Intent(ProfileActivity.this, AddLandmarkActivity.class);
        intent.putExtra("username", username);
        startActivityForResult(intent, ADD_LANDMARK_REQUEST_CODE);
    }

    public void goHome() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode == ADD_LANDMARK_REQUEST_CODE) {
            if(data == null) return;
            Landmark landmark = (Landmark)data.getSerializableExtra("Object");
            listLandmarks.add(landmark);
        }
    }
}
