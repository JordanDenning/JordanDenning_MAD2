package com.example.lab6;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dogTextView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dogTextView = itemView.findViewById(R.id.textView);
            dogTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            itemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    private List<Dog>mDogs;

    public DogAdapter(List<Dog> dogs, ListItemClickListener dogClickListener){
        mDogs = dogs;
        itemClickListener = dogClickListener;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View dogView = inflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(dogView);
        return viewHolder;
    }

    @Override
    public void  onBindViewHolder(@NonNull DogAdapter.ViewHolder viewHolder, int i){
        Dog dog = mDogs.get(i);
        viewHolder.dogTextView.setText(dog.getName());
    }

    @Override
    public  int getItemCount(){
        return  mDogs.size();
    }


    public interface ListItemClickListener{
        void onListItemClick(int position);
    }

    ListItemClickListener itemClickListener;

}
