package com.example.proiectdam_serbansorinaalexandra.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;
import com.example.proiectdam_serbansorinaalexandra.R;

public class SignInActivity extends AppCompatActivity {

    private Button btnSignIn;
    private EditText etUsername;
    private EditText etPassword;
    private DatabaseInstance database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        database = DatabaseInstance.getInstance(this);
        initViews();
        activateListeners();
    }

    public void initViews() {
        btnSignIn = findViewById(R.id.btn_sign_in);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
    }

    public void activateListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateLogin()) {
                    openProfileActivity();
                }
            }
        });
    }

    public boolean validateLogin() {
        if(database.getUserDAO().userExists(etUsername.getText().toString(),etPassword.getText().toString()) == 0) {
            etPassword.setError("Invalid password or username does not exist!");
            return false;
        }
        if(etUsername.getText().toString().isEmpty()) {
            etUsername.setError("Enter the username!");
            return false;
        }
        if(etPassword.getText().toString().isEmpty()) {
            etPassword.setError("Enter the password!");
            return false;
        }
        return true;
    }

    public void openProfileActivity() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("username", etUsername.getText().toString());
        startActivity(intent);
    }
}
