package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RecalculationScreen extends AppCompatActivity {

    EditText username, savings, expences, free;
    Button change;

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
                    Intent intent = new Intent(getApplicationContext(), NewOverview.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });
    }
}