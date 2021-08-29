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
    public static double SAVINGS_EXPENSES_VALUE = 0.40;
    public static double FREE_MONEY_VALUE = 0.20;
    public static double savings = 0.0;
    public static double free = 0.0;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        displaySavings = findViewById(R.id.savings);
        displayOutcome = findViewById(R.id.outcome);
        displayFree = findViewById(R.id.free);

        // Get and calculate the values
        if(this.counter == 0){
            Intent intent = getIntent();
            savings = Double.parseDouble(intent.getStringExtra("income"));
            this.counter += 1;
        }
        free = savings;
        free = FREE_MONEY_VALUE * free;
        savings = SAVINGS_EXPENSES_VALUE * savings;
        displaySavings.setText(String.format("   %.2f   ", savings));
        displayOutcome.setText(String.format("   %.2f   ", savings));
        displayFree.setText(String.format("   %.2f   ", free));


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