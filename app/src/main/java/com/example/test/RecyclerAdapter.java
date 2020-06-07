package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextViewLine1;
        public TextView mTextViewLine2;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.item_title);
            mTextViewLine2 = itemView.findViewById(R.id.item_detail);
            mTextViewLine1.setEnabled(false);
            mTextViewLine2.setEnabled(false);
        }
    }

    public RecyclerAdapter(ArrayList<ExampleItem> exampleList) {
            mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mylayoutregister2, viewGroup, false);
        ExampleViewHolder viewHolder= new ExampleViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder viewHolder, int position) {
        ExampleItem currentItem = mExampleList.get(position);
        viewHolder.mTextViewLine1.setText(currentItem.getText1());
        viewHolder.mTextViewLine2.setText(currentItem.getText2());
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}



