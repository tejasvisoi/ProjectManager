package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
public class registration extends AppCompatActivity {
    boolean check;
    String a[];
    RecyclerView r;
    String s1[];
    private adapter adapter;
    public ArrayList<modelclass> editModelArrayList;
    DatabaseReference databasereference;
    Button reg,reg1;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        r=(RecyclerView)findViewById(R.id.r);
        s1=getResources().getStringArray(R.array.textfield);
        reg=(Button)findViewById(R.id.register);
        editModelArrayList = populateList();
        adapter = new adapter(this,editModelArrayList,s1);
        r.setAdapter(adapter);
        a = new String[adapter.editModelArrayList.size()];
        r.setLayoutManager(new LinearLayoutManager(this));
        databasereference=FirebaseDatabase.getInstance().getReference("Students");
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for (int i = 0; i < adapter.editModelArrayList.size(); i++){
                        a[i]=adapter.editModelArrayList.get(i).getEditTextValue();
                    }
                    mAuthStateListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser mfirebaseuser = mAuth.getCurrentUser();
                        if (mfirebaseuser != null) {
                            users info=new users(a[0],a[1],a[2],a[3],a[4]);
                            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(registration.this,"Registration Complete",Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(registration.this, "Please log In", Toast.LENGTH_SHORT).show();

                        }

                    }
                };
            }
        });
    }
    private ArrayList<modelclass> populateList(){

        ArrayList<modelclass> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            modelclass editModel = new modelclass();
            editModel.setEditTextValue(String.valueOf(""));
            list.add(editModel);
        }

        return list;
    }
}
