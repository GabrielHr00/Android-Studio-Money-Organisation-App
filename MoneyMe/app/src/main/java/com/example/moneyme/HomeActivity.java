package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Button calculate;
    EditText income;
    DBHelper db;

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
    }
}