package com.example.proiectdam_serbansorinaalexandra.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectdam_serbansorinaalexandra.Data.User;
import com.example.proiectdam_serbansorinaalexandra.Database.DatabaseInstance;
import com.example.proiectdam_serbansorinaalexandra.MainActivity;
import com.example.proiectdam_serbansorinaalexandra.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cb;
    private Button btnRegistration;

    private DatabaseInstance database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = DatabaseInstance.getInstance(this);
        setContentView(R.layout.activity_register);
        initViews();
        activateListeners();
    }

    public void initViews() {
        btnRegistration = findViewById(R.id.btn_registration);
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etUsername = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        cb = findViewById(R.id.cb_terms);
    }

    public void activateListeners() {
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateRegistration()) {
                    createNewUser();
                    Toast.makeText(RegisterActivity.this, "User created!", Toast.LENGTH_LONG).show();
                    startMainActivity();
                }
            }
        });
    }

    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean validateRegistration() {
        if(etUsername.getText().toString().length() < 6) {
            etUsername.setError("Enter at least 6 characters!");
            return false;
        }
        if(database.getUserDAO().checkUsername(etUsername.getText().toString()) != 0) {
            etUsername.setError("This username already exists!");
            return false;
        }
        if(etFirstName.getText().toString().isEmpty()) {
            etFirstName.setError("Empty field!");
            return false;
        }
        if(etLastName.getText().toString().isEmpty()) {
            etLastName.setError("Empty field!");
            return false;
        }
        if(etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Empty field!");
            return false;
        }
        if(etPassword.getText().toString().length() < 6) {
            etPassword.setError("Password must contain at least 6 characters!");
            return false;
        }
        if(!cb.isChecked()) {
            cb.setError("You must agree to terms and conditions!");
            return false;
        }
        return true;
    }

    public void createNewUser() {
        User newUser = new User();
        newUser.username = etUsername.getText().toString();
        newUser.firstName = etFirstName.getText().toString();
        newUser.lastName = etLastName.getText().toString();
        newUser.email = etEmail.getText().toString();
        newUser.password = etPassword.getText().toString();
        database.getUserDAO().insertUser(newUser);
    }
}
