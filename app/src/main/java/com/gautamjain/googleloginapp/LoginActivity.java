package com.gautamjain.googleloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView signup;
    private Button loginbtn;
    private EditText inputemail, inputpassword;
    private FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = findViewById(R.id.registerFromLogin);
        loginbtn = findViewById(R.id.Loginbtn);

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(LoginActivity.this);

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
        else
        {
           pd.setTitle("Login");
           pd.setMessage("Loading");
           pd.setCanceledOnTouchOutside(false);
           pd.show();

           mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       pd.dismiss();
                       Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent);
                   }
               }
           });
        }
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus(); // Focus to show error
    }

}