package com.example.projecttwo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {
   List<WineData> values;
    Context context1;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;

        public ViewHolder(View v){
            super(v);
            textView = (TextView) v.findViewById(R.id.textViewName);
            textView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v){
            itemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public  RecyclerViewAdapter(Context context2, List<WineData> values2, ListItemClickListener wineClickListener){
        values = values2;
        context1 = context2;
        itemClickListener = wineClickListener;
    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view1 = LayoutInflater.from(context1).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vholder, int position){
        vholder.textView.setText(values.get(position).name);
        vholder.textView.setBackgroundColor(Color.parseColor("#c62828"));
        vholder.textView.setTextColor(Color.BLACK);

    }

    @Override
    public  int getItemCount(){
        if (values!=null) {
            return values.size();
        }
        else {
            return 0;
        }
    }

    public interface ListItemClickListener{
        void onListItemClick(int position);
    }

    ListItemClickListener itemClickListener;


}
