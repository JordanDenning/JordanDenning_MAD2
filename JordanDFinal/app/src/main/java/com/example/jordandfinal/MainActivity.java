package com.example.jordandfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<RestData> restData = new ArrayList<>();
    DatabaseReference restRef = FirebaseDatabase.getInstance().getReference("Rest");

    FirebaseRecyclerAdapter adapter;
    View view;
    RecyclerView recyclerView;
    Context context;
    RecyclerView.LayoutManager recyclerViewLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Query query = FirebaseDatabase.getInstance().getReference().child("Rest");
        SnapshotParser<RestData> parser = new SnapshotParser<RestData>() {
            @NonNull
            @Override
            public RestData parseSnapshot(@NonNull DataSnapshot snapshot) {
                RestData newRest = new RestData(snapshot.getKey(),
                        snapshot.child("name").getValue().toString(),
                        snapshot.child("url").getValue().toString());
                restData.add(newRest);
                Log.d("restname", newRest.name);
                return newRest;
            }
        };

        //define adapter options
        FirebaseRecyclerOptions<RestData> options = new FirebaseRecyclerOptions.Builder<RestData>()
                .setQuery(query, parser)
                .build();

        //create an adapter
        adapter = new FirebaseRecyclerAdapter<RestData, RestViewHolder>(options) {

            @NonNull
            @Override
            public RestViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
                return new RestViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final RestViewHolder holder, int position, @NonNull final RestData model) {
                holder.setRestName(model.getName());

                //onclick listener
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();

                        String restURL = restData.get(position).getUrl();
                        //create new intent
                        Intent intent = new Intent(MainActivity.this, RestWebView.class);
                        //add url to intent
                        intent.putExtra("RestURL", restURL);
                        //start intent
                        startActivity(intent);
                    }
                });

                //context menu
                holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
                        //set the menu title
                        menu.setHeaderTitle("Delete Restaurant?");
                        //add the choices to the menu
                        menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //get recipe that was pressed
                                int position = holder.getAdapterPosition();
                                //get recipe id
                                String recipeid = restData.get(position).getId();
                                //delete from Firebase
                                restRef.child(recipeid).removeValue();
                                Snackbar.make(v, "Restaurant Deleted.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                return false;
                            }
                        });
                        menu.add(2, 2, 2, "No");
                    }
                });
            }
        };

        // get the recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //assign the adapter to the recycle view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a vertical linear layout to hold edit texts
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                //create edit texts and add to layout
                final EditText nameEditText = new EditText(MainActivity.this);
                nameEditText.setHint("Restaurant name");
                layout.addView(nameEditText);
                final EditText urlEditText = new EditText(MainActivity.this);
                urlEditText.setHint("Restaurant URL");
                layout.addView(urlEditText);

                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Restaurant");
                dialog.setView(layout);
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get entered data
                        String restName = nameEditText.getText().toString();
                        String restURL = urlEditText.getText().toString();
                        if (restName.trim().length() > 0) {
                            //get new id from firebase
                            String key = restRef.push().getKey();
                            //create new item
                            RestData newRecipe = new RestData(key, restName, restURL);
                            //add to Firebase
                            restRef.child(key).child("name").setValue(newRecipe.getName());
                            restRef.child(key).child("url").setValue(newRecipe.getUrl());
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.show();
            }
        });
    }





    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}

