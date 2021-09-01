package com.example.moneyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Overview extends AppCompatActivity {
    TextView displaySavings, displayOutcome, displayFree;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        displaySavings = findViewById(R.id.savings);
        displayOutcome = findViewById(R.id.outcome);
        displayFree = findViewById(R.id.free);
        DBHelper db = new DBHelper(this);

        // calculate income values
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        String inc = db.getIncome(username);

        double savings = Double.parseDouble(db.getSavings(username))/100.0;
        double expences = Double.parseDouble(db.getExpences(username))/100.0;
        double free = Double.parseDouble(db.getFree(username))/100.0;

        if(!inc.equals("")){
            double income = Double.parseDouble(inc);
            savings = income * savings;
            expences  = income * expences;
            free = income * free;

            displaySavings.setText(String.format("   %.2f   ", savings));
            displayOutcome.setText(String.format("   %.2f   ", expences));
            displayFree.setText(String.format("   %.2f   ", free));
        }

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
}