package com.gautamjain.googleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    TextView signin;
    Button Registerbtn;
    EditText inputemail, inputpassword, inputconfirmPassword, inputusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signin = findViewById(R.id.loginFromRegister);
        Registerbtn = findViewById(R.id.Registerbtn);

        inputemail = findViewById(R.id.inputEmail);
        inputusername =findViewById(R.id.inputUsername);
        inputpassword = findViewById(R.id.inputPassword);
        inputconfirmPassword = findViewById(R.id.inputConfirmPassword);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkCredentials();
            }
        });

    }

    private void checkCredentials() {

        String username = inputusername.getText().toString();
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();
        String confirmPassword = inputconfirmPassword.getText().toString();

        if(username.isEmpty() || username.length() < 6)
        {
            showError(inputusername, "Your name is too Short!");
        }
        else if(email.isEmpty() || !email.contains("@"))
        {
            showError(inputemail, "Email is not valid!");
        }
        else if(password.isEmpty() || password.length() < 8)
        {
            showError(inputpassword, "Password too short!");
        }
        else if(confirmPassword.isEmpty() || !confirmPassword.equals(password))
        {
            showError(inputconfirmPassword, "Password not Match!");
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus(); // Focus to show error
    }


}