package com.example.test;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class homepage extends AppCompatActivity{

    Animation left;
    View img1,img2,img3,img4,img21,img41,img31;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        img1=(View)findViewById(R.id.side);
        img2=(View)findViewById(R.id.profile);
        img21=(View)findViewById(R.id.profile1);
        img3=(View)findViewById(R.id.contact);
        img31=(View)findViewById(R.id.contact1);
        img4=(View)findViewById(R.id.task1);
        img41=(View)findViewById(R.id.task);
        left= AnimationUtils.loadAnimation(this,R.anim.left);
        img1.setAnimation(left);
        img2.setAnimation(left);
        img3.setAnimation(left);
        img4.setAnimation(left);

    }

    public void changefragment(View view){
        Fragment fragment;
        if(view==(findViewById(R.id.task1))){
            img2.setVisibility(View.VISIBLE);
            img21.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.INVISIBLE);
            img41.setVisibility(View.VISIBLE);
            img31.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.VISIBLE);
            fragment = new Task();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,fragment);
            ft.commit();
        }
        if(view==(findViewById(R.id.profile))){
            img2.setVisibility(View.INVISIBLE);
            img21.setVisibility(View.VISIBLE);
            img4.setVisibility(View.VISIBLE);
            img41.setVisibility(View.INVISIBLE);
            img31.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.VISIBLE);
            fragment = new Profile();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,fragment);
            ft.commit();
        }
        if(view==(findViewById(R.id.contact))){
            img2.setVisibility(View.VISIBLE);
            img21.setVisibility(View.INVISIBLE);
            img4.setVisibility(View.VISIBLE);
            img41.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
            img31.setVisibility(View.VISIBLE);
            fragment = new contact();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.replace(R.id.fragment,fragment);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed(){
        return;

    }

}
