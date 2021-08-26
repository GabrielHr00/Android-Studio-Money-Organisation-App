package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {
    Button registerButton, logIn;
    EditText username, password;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        registerButton = findViewById(R.id.register_button);
        logIn = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        db = new DBHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, RegisterScreen.class));
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LogIn.this, "Please make sure, that the both fields are filled", Toast.LENGTH_SHORT).show();
                }else{
                    boolean checkPass = db.checkPassword(user, pass);
                    if(checkPass == true){
                        Toast.makeText(LogIn.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("user", user);
                        intent.putExtra("pass", pass);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LogIn.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}