package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends Fragment {
    DatabaseReference reff;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        View view = (View) rootView.findViewById(R.id.logout);
        View view1 = (View) rootView.findViewById(R.id.next);
        final TextView et1=(TextView)rootView.findViewById(R.id.editText1);
        final TextView et2=(TextView)rootView.findViewById(R.id.editText2);
        final TextView et3=(TextView)rootView.findViewById(R.id.editText3);
        final TextView et4=(TextView)rootView.findViewById(R.id.editText4);
        final TextView et5=(TextView)rootView.findViewById(R.id.editText5);
        final TextView et6=(TextView)rootView.findViewById(R.id.editText6);
                reff= FirebaseDatabase.getInstance().getReference("Students").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String roll=dataSnapshot.child("rollno").getValue().toString();
                        String name=dataSnapshot.child("name").getValue().toString();
                        String bat=dataSnapshot.child("batch").getValue().toString();
                        String dept=dataSnapshot.child("depart").getValue().toString();
                        String crs=dataSnapshot.child("course").getValue().toString();
                        String email=dataSnapshot.child("email").getValue().toString();
                        et1.setText(roll);
                        et2.setText(name);
                        et3.setText(bat);
                        et4.setText(dept);
                        et5.setText(crs);
                        et6.setText(email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i1=new Intent(getActivity(),login.class);
                startActivity(i1);
            }
        });
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }


}