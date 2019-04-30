package com.example.projecttwo;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment {

    List<FavWineData> wines = new ArrayList<>();

    DatabaseReference wineRef = FirebaseDatabase.getInstance().getReference("Wine");


    FirebaseRecyclerAdapter adapter;
    View view;
    RecyclerView recyclerView;
    Context context;
    RecyclerView.LayoutManager recyclerViewLayoutManager;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("are you working?", "hello");
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_favorites, container, false);
        context=view.getContext();
        Query query = FirebaseDatabase.getInstance().getReference().child("Wine");
        SnapshotParser<FavWineData> parser = new SnapshotParser<FavWineData>() {
            @NonNull
            @Override
            public FavWineData parseSnapshot(@NonNull DataSnapshot snapshot) {
                FavWineData newWine = new FavWineData(snapshot.getKey(),
                        snapshot.child("name").getValue().toString(),
                        snapshot.child("region").getValue().toString(),
                        snapshot.child("price").getValue().toString(),
                        snapshot.child("winery").getValue().toString(),
                        snapshot.child("varietal").getValue().toString(),
                        snapshot.child("vintage").getValue().toString());
                        wines.add(newWine);
                        Log.d("wineName", newWine.name);
                        return newWine;
            }
        };

        //define adapter options
        FirebaseRecyclerOptions<FavWineData> options = new FirebaseRecyclerOptions.Builder<FavWineData>()
                .setQuery(query, parser)
                .build();

        //create an adapter
        adapter = new FirebaseRecyclerAdapter<FavWineData, WineViewHolder>(options) {

            @NonNull
            @Override
            public WineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
                return new WineViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final WineViewHolder holder, int position, @NonNull final FavWineData model) {
                Log.d("are you setting", model.getName());
                holder.setWineName(model.getName());
                holder.setBackgroundColor(Color.parseColor("#b71c1c"));
                holder.setTextColor(Color.BLACK);

                //onclick listener
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getAdapterPosition();
                        Fragment fragment = new FavSpecificWine();
                        String name = wines.get(position).getName();
                        String region = wines.get(position).getRegion();
                        String varietal = wines.get(position).getVarietal();
                        String price = wines.get(position).getPrice();
                        String vintage = wines.get(position).getVintage();
                        String winery = wines.get(position).getWinery();



                        ((FavSpecificWine) fragment).getText(name, region, price, varietal, vintage, winery);
                        loadFragment(fragment);
                    }
                });

                //context menu
                holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
                        //set the menu title
                        menu.setHeaderTitle("Delete Wine?");
                        //add the choices to the menu
                        menu.add(1, 1, 1, "Yes").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                //get recipe that was pressed
                                int position = holder.getAdapterPosition();
                                //get recipe id
                                String recipeid = wines.get(position).getId();
                                //delete from Firebase
                                wineRef.child(recipeid).removeValue();
                                Snackbar.make(v, "Wine Deleted.", Snackbar.LENGTH_LONG)
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        //assign the adapter to the recycle view
        recyclerView.setAdapter(adapter);

        return view;

        //set a layout manager on the recycler view

    }

    private void loadFragment(Fragment fragment){
        if(fragment != null){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_recipe_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}