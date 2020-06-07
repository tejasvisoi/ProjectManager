package com.example.test;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.MyOwnHolder> {
    private LayoutInflater inflater;
    public static ArrayList<modelclass> editModelArrayList;
    String data1[];
    Context ct;

    public adapter(Context ctx, ArrayList<modelclass> editModelArrayList,String s1[]){
        ct=ctx;
        data1=s1;
        this.editModelArrayList = editModelArrayList;
    }

    @NonNull
    @Override
    public adapter.MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.mylayoutregister, parent, false);
        MyOwnHolder holder = new MyOwnHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnHolder holder, int position) {
        holder.editText.setText(editModelArrayList.get(position).getEditTextValue());
        holder.t1.setText(data1[position]);
        Log.d("print","yes");
    }

    @Override
    public int getItemCount() {
        return editModelArrayList.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder{
        EditText editText;
        TextView t1;
        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            editText = (EditText) itemView.findViewById(R.id.editText);
            t1=(TextView)itemView.findViewById(R.id.textview);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    editModelArrayList.get(getAdapterPosition()).setEditTextValue(editText.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {


                }
            });


        }
    }
}
