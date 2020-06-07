package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Task extends Fragment  {

    private ArrayList<ExampleItem> mExampleList;
    String title,msg;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        loadData();
        final View rootView = inflater.inflate(R.layout.fragment_task, container, false);

        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mhand, new IntentFilter("com.example.test_FCM-MESSAGE"));
        mRecyclerView =(RecyclerView)rootView.findViewById(R.id.recycler_view);
        buildRecyclerView();

        return rootView;
    }
    private BroadcastReceiver mhand=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            title=intent.getStringExtra("title");
            msg=intent.getStringExtra("message");
            int position = mExampleList.size();
            insertItem(position);
        }
    };
    public void insertItem(int position) {
        mExampleList.add(position, new ExampleItem(title , msg));
        mAdapter.notifyItemInserted(position);
    }

    @Override
    public void onPause() {
        super.onPause();
        saveData();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mhand);
    }
    public void buildRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new RecyclerAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void saveData() {
        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.commit();
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ExampleItem>>() {
        }.getType();
        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        saveData();
    }
}
