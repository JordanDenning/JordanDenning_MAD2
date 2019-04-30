package com.example.projecttwo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class WineViewHolder extends RecyclerView.ViewHolder {
    private TextView wineName;

    public  WineViewHolder(@NonNull View newView){
        super(newView);
        wineName = newView.findViewById(R.id.textViewName);
    }

    public void setWineName(String name){
        wineName.setText(name);
    }

    public void setBackgroundColor(int color){
        wineName.setBackgroundColor(color);
    }

    public void setTextColor(int color){
        wineName.setTextColor(color);
    }
}
