package com.example.moneyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class RecalculationScreen extends AppCompatActivity {

    EditText username, savings, expences, free;
    Button change;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recalculation_screen);

        username = findViewById(R.id.username);
        savings = findViewById(R.id.savingsPer);
        expences = findViewById(R.id.expences);
        free = findViewById(R.id.moneyMeTitle);
        DBHelper db = new DBHelper(this);


        change = findViewById(R.id.calculate);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString() != "" && savings.getText().toString() != ""
                        && expences.getText().toString() != "" && free.getText().toString() != ""){

                    String user = username.getText().toString();
                    String save = savings.getText().toString();
                    String exp = expences.getText().toString();
                    String fre = free.getText().toString();

                    db.changePercentage(user, save, exp, fre);
                    Intent intent = new Intent(getApplicationContext(), Overview.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
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
                        startActivity(new Intent(getApplicationContext(), MoneyScreen.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.progress:
                        startActivity(new Intent(getApplicationContext(), Progress.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
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
}