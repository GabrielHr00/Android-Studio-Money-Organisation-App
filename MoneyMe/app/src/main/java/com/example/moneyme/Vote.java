package com.example.moneyme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Vote extends AppCompatActivity implements View.OnClickListener {
    BottomNavigationView navigationView;
    ImageButton zero, thirty, sixty, hundred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        // set image buttons on click listener
        zero = findViewById(R.id._0);
        thirty = findViewById(R.id._30);
        sixty = findViewById(R.id._65);
        hundred = findViewById(R.id._100);

        zero.setOnClickListener(this);
        thirty.setOnClickListener(this);
        sixty.setOnClickListener(this);
        hundred.setOnClickListener(this);


        // Navigation Bar
        navigationView = findViewById(R.id.navigation);
        navigationView.setSelectedItemId(R.id.vote);
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.vote:
                        return true;
                    case R.id.money:
                        startActivity(new Intent(getApplicationContext(), Overview.class));
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
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        DBHelper db = new DBHelper(Vote.this);
        switch(v.getId()){
            case R.id._0:
                db.voteEmoji(3, "zero");
                Toast.makeText(Vote.this, "Vote successfully counted! ", Toast.LENGTH_SHORT).show();
                break;
            case R.id._30:
                db.voteEmoji(4, "thirty");
                Toast.makeText(Vote.this, "Vote successfully counted! ", Toast.LENGTH_SHORT).show();
                break;
            case R.id._65:
                db.voteEmoji(5, "sixty");
                Toast.makeText(Vote.this, "Vote successfully counted! ", Toast.LENGTH_SHORT).show();
                break;
            case R.id._100:
                db.voteEmoji(6, "hundred");
                Toast.makeText(Vote.this, "Vote successfully counted! ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}