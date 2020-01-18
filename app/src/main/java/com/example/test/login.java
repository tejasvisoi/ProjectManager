package com.example.test;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import me.itangqi.waveloadingview.WaveLoadingView;

public class login extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    Button login, signup;
    ProgressBar pb;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.user);
        editTextPassword = (EditText) findViewById(R.id.pass);

        login = (Button) findViewById(R.id.logon);
        signup = (Button) findViewById(R.id.signup);
        pb = (ProgressBar)findViewById(R.id.progressbar);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfirebaseuser = mAuth.getCurrentUser();
                if (mfirebaseuser != null) {
                    Toast.makeText(login.this, "Welcome Back!", Toast.LENGTH_SHORT).show();
                    Intent I1 = new Intent(login.this, homepage.class);
                    startActivity(I1);

                } else {
                    Toast.makeText(login.this, "Please log In", Toast.LENGTH_SHORT).show();

                }

            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                } else if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(login.this, "Fields are empty", Toast.LENGTH_SHORT).show();

                } else if (password.isEmpty()) {
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();

                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please enter a valid email");
                    editTextEmail.requestFocus();

                }else if (password.length() < 6) {
                    editTextPassword.setError("Minimum length of password should be 6");
                    editTextPassword.requestFocus();

                }

                else if (!(email.isEmpty() && password.isEmpty())) {
                    pb.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "Password and Email do not match", Toast.LENGTH_SHORT).show();
                                pb.setVisibility(View.GONE);
                            }
                            else {
                                Intent i2 = new Intent(login.this, homepage .class);
                                startActivity(i2);
                                finish();
                            }
                        }
                    });
                } else
                    Toast.makeText(login.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(login.this, signup.class);
                startActivity(i3);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);

    }
    @Override
    public void onBackPressed(){
        return;
    }

}

