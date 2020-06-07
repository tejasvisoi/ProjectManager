package com.example.test;

import android.content.Intent;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signup extends AppCompatActivity {

    EditText editTextEmail, editTextPassword,editTextPassword1;
    private FirebaseAuth mAuth;
    Button signup,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.user);
        editTextPassword = (EditText) findViewById(R.id.pass);
        editTextPassword1 = (EditText) findViewById(R.id.pass2);
        signup=(Button)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String password1 = editTextPassword1.getText().toString().trim();
                if (email.isEmpty()) {
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();

                }

                else if (email.isEmpty()&&password.isEmpty()&&password1.isEmpty()) {
                    Toast.makeText(signup.this,"Fields are empty",Toast.LENGTH_SHORT).show();

                }

                else if (password.isEmpty()) {
                    editTextPassword.setError("Password is required");
                    editTextPassword.requestFocus();

                }
                else if(!password.matches(password1)){
                    editTextPassword1.setError("Passwords do not match");
                    editTextPassword1.requestFocus();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please enter a valid email");
                    editTextEmail.requestFocus();
                }

                else if (password.length() < 6) {
                    editTextPassword.setError("Minimum length of password should be 6");
                    editTextPassword.requestFocus();

                }
                else if(!(email.isEmpty()&&password.isEmpty())){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(signup.this,"Email Already Exists",Toast.LENGTH_SHORT).show();
                            }
                            else {

                                startActivity(new Intent(signup.this, registration.class));
                                finish();
                            }
                        }
                    });
                }
                else
                    Toast.makeText(signup.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(signup.this, login.class);
                startActivity(i1);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){
        Intent i1=new Intent(signup.this, login.class);
        startActivity(i1);
        finish();
    }
}
