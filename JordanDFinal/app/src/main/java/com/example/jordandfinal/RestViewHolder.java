package com.example.jordandfinal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class RestViewHolder extends RecyclerView.ViewHolder {
    private TextView recipename;

    public RestViewHolder(@NonNull View itemView) {
        super(itemView);
        recipename = itemView.findViewById(R.id.restTextView);
    }

    public void setRestName(String name){
        recipename.setText(name);
    }
}