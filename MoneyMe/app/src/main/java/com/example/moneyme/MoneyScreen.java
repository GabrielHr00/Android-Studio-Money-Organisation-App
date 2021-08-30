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

public class MoneyScreen extends AppCompatActivity {
    Button calculate;
    EditText income;
    DBHelper db;
    BottomNavigationView navigationView;
    static boolean isAlreadyRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_screen);

        calculate = findViewById(R.id.calculate);
        income = findViewById(R.id.moneyMeTitle);
        db = new DBHelper(this);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String income1 = income.getText().toString();

                if (income1.equals("")) {
                    Toast.makeText(MoneyScreen.this, "Please enter a valid income!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = getIntent();
                    String username = intent.getStringExtra("user");
                    String password = intent.getStringExtra("pass");

                    // insert an income value and send it to Overview
                    Intent intent1 = new Intent(getApplicationContext(), Overview.class);
                    isAlreadyRun = true;
                    db.insertIncome(username, password, income1);
                    intent1.putExtra("income", income1);
                    startActivity(intent1);
                }
            }
        });

        // Navigation Bar
        navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.money);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.vote:
                        startActivity(new Intent(getApplicationContext(), Vote.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.money:
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
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public static boolean isAlreadyRun(){
        return isAlreadyRun;
    }
}