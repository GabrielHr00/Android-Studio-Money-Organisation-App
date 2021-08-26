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

public class HomeActivity extends AppCompatActivity {
    Button calculate;
    EditText income;
    DBHelper db;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        calculate = findViewById(R.id.calculate);
        income = findViewById(R.id.income);

        db = new DBHelper(this);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inco = income.getText().toString();
                if(inco.equals("")){
                    Toast.makeText(HomeActivity.this, "Please enter a valid income!", Toast.LENGTH_SHORT).show();
                }
                else{
                    // Get User credentials
                    Intent intent = getIntent();
                    String username = intent.getStringExtra("user");
                    String password = intent.getStringExtra("pass");

                    // insert an income value and send it to Overview
                    Intent intent2 = new Intent(getApplicationContext(), Overview.class);
                    intent2.putExtra("income", inco);
                    db.insertIncome(username, password, inco);
                    startActivity(intent2);
                }
            }
        });


        // Navigation Bar
        navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.person:
                        startActivity(new Intent(getApplicationContext(), LogIn.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.progress:
                        startActivity(new Intent(getApplicationContext(), Progress.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(), About.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                }
                return false;
            }
        });
    }
}