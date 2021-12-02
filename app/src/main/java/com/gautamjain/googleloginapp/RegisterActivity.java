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

public class RegisterActivity extends AppCompatActivity {

    private TextView signin;
    private Button Registerbtn;
    private EditText inputemail, inputpassword, inputconfirmPassword, inputusername;

    private FirebaseAuth mAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signin = findViewById(R.id.loginFromRegister);
        Registerbtn = findViewById(R.id.Registerbtn);

        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(RegisterActivity.this);

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
        else
        {
            pd.setTitle("Registration");
            pd.setMessage("Loading...");
            pd.setCanceledOnTouchOutside(false);
            pd.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                        pd.dismiss();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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