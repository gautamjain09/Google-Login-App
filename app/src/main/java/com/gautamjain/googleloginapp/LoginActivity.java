package com.gautamjain.googleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView signup;
    Button loginbtn;
    EditText inputemail, inputpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.registerFromLogin);
        loginbtn = findViewById(R.id.Loginbtn);

        inputemail = findViewById(R.id.inputEmail);
        inputpassword = findViewById(R.id.inputPassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              checkCredentials();
            }
        });
    }

    private void checkCredentials() {

        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();

        if(email.isEmpty() || !email.contains("@"))
        {
            showError(inputemail, "Email is not valid!");
        }
        else if(password.isEmpty() || password.length() < 8)
        {
            showError(inputpassword, "Password too short!");
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus(); // Focus to show error
    }

}