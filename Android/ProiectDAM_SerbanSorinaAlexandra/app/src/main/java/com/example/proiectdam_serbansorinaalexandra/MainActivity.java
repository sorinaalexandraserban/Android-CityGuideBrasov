package com.example.proiectdam_serbansorinaalexandra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.proiectdam_serbansorinaalexandra.Database.SharedPrefs;
import com.example.proiectdam_serbansorinaalexandra.UI.RegisterActivity;
import com.example.proiectdam_serbansorinaalexandra.UI.SignInActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button btnSignIn;
    private Button btnRegister;
    private Button btnPreferences;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        sharedPrefs = SharedPrefs.getInstance(getApplicationContext());
        savePrefs();
        activateListeners();
    }

    private void initViews() {
        btnSignIn = findViewById(R.id.btn_signin);
        btnRegister = findViewById(R.id.btn_register);
        btnPreferences = findViewById(R.id.btn_preferences);
    }

    private void savePrefs() {
        sharedPrefs.writeInt("version", 1);
        sharedPrefs.writeString("app_name", "cityguide");
    }

    private void activateListeners() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSingInActivity();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterForm();
            }
        });
        btnPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intValue = sharedPrefs.readInt("version");
                String stringValue = sharedPrefs.readString("app_name");
                Toast.makeText(MainActivity.this, "version = "+intValue+" ; app_name = "+stringValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openSingInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    private void openRegisterForm() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
    }
}