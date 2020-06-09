package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static int timeout = 2300;
    ImageView img,img2;
    Animation bottom;
    Animation top;
    Button login;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.imageView);
        img2=(ImageView)findViewById(R.id.imageView2);
        top=AnimationUtils.loadAnimation(this,R.anim.movemyface);
        img2.setAnimation(top);
        bottom= AnimationUtils.loadAnimation(this,R.anim.movemyass);
        img.setAnimation(bottom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getIntent().hasExtra("title")){
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivity(intent);
                    finish();

                } else {
                    startActivity(new Intent(MainActivity.this, login.class));
                    finish();
                }

            }
        }, timeout);

    }
}
