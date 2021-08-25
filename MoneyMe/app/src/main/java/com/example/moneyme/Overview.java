package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Overview extends AppCompatActivity {
    TextView displayIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        displayIncome = findViewById(R.id.income);
        Intent intent = getIntent();
        String income = intent.getStringExtra("income");
        displayIncome.setText(income);
    }
}