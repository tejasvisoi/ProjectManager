package com.example.test;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class login extends AppCompatActivity {
    private static String data;
    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;
    Button login, signup;
    ProgressBar pb;
    TextView newuser;
    private static final String TAG = "login";
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.user);
        editTextPassword = (EditText) findViewById(R.id.pass);
        newuser=(TextView)findViewById(R.id.newuser);
        login = (Button) findViewById(R.id.logon);
        signup = (Button) findViewById(R.id.signup);
        pb = (ProgressBar)findViewById(R.id.progressbar);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NotNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfirebaseuser = mAuth.getCurrentUser();
                if (mfirebaseuser != null) {
                    FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                @Override
                                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                    if (!task.isSuccessful()) {
                                        Log.w(TAG, "getInstanceId failed", task.getException());
                                        return;
                                    }

                                    // Get new Instance ID token
                                    String token = task.getResult().getToken();

                                    // Log and toast
                                    String msg = getString(R.string.msg_token_fmt, token);

                                }
                            });
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
                    newuser.setVisibility(View.INVISIBLE);
                    login.setVisibility(View.INVISIBLE);
                    signup.setVisibility(View.INVISIBLE);
                    pb.setVisibility(View.VISIBLE);



                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NotNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "Password and Email do not match", Toast.LENGTH_SHORT).show();
                                newuser.setVisibility(View.VISIBLE);
                                login.setVisibility(View.VISIBLE);
                                signup.setVisibility(View.VISIBLE);
                                pb.setVisibility(View.GONE);

                            }
                            else {
                                data=editTextEmail.getText().toString();
                                Intent i2 = new Intent(login.this, homepage.class);
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
    public static String getdata(){
        return data;
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

