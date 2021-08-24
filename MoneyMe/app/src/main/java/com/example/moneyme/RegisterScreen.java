package com.example.moneyme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterScreen extends AppCompatActivity {
    Button registerButton;
    EditText username, password, repass;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        registerButton = findViewById(R.id.register_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repass = findViewById(R.id.repassword);
        db = new DBHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repas = repass.getText().toString();

                if(user.equals("") || pass.equals("") || repas.equals("")){
                    Toast.makeText(RegisterScreen.this, "Please enter a password or username", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(repas)){
                        boolean checkUser = db.checkUsername(user);
                        if(checkUser == false){
                            boolean insert = db.insertData(user, pass);
                            if(insert == true){
                                Toast.makeText(RegisterScreen.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterScreen.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterScreen.this, "User already exists, please sign in!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterScreen.this, "The two passwords do not match, please reenter!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}