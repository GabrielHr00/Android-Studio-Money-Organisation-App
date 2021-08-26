package com.example.moneyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LogIn extends AppCompatActivity {
    Button registerButton, logIn;
    EditText username, password;
    DBHelper db;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

//        // Navigation Bar
//        navigationView = findViewById(R.id.navigation);
//        navigationView.setSelectedItemId(R.id.person);
//        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.person:
//                        return true;
//                    case R.id.money:
//                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.progress:
//                        startActivity(new Intent(getApplicationContext(), Progress.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.info:
//                        startActivity(new Intent(getApplicationContext(), About.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                    case R.id.home:
//                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                        overridePendingTransition(0, 0);
//                        return true;
//                }
//                return false;
//            }
//        });


    }
}