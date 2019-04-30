package com.example.projecttwo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoadedWine extends Fragment implements RecyclerViewAdapter.ListItemClickListener {
    View view;
    RecyclerView recyclerView;
    Context context;
    RecyclerView.Adapter recyclerView_adapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    List<WineData> wineData = new ArrayList<>();
    private static final String API_URL = "https://api.snooth.com/wines/?akey=65x5832y013kw1hmvytti3p4xkfott3skvw6e5agp110n5lw&n=20&color=red";



    public LoadedWine() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_loaded_wine, container, false);
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView_adapter = new RecyclerViewAdapter(context, wineData, this);
        recyclerView.setAdapter(recyclerView_adapter);

        if(savedInstanceState != null){
            Log.d("not null", "saved instacne state was not null");
            int size = savedInstanceState.getInt("size");
            String Ssize = Integer.toString(size);
            Log.d("size", Ssize);

            for(int i = 0; i < size; i++) {
                String name = savedInstanceState.getString("name"+i);
                String region = savedInstanceState.getString("region"+i);
                String price = savedInstanceState.getString("price"+i);
                String winery = savedInstanceState.getString("winery"+i);
                String varietal = savedInstanceState.getString("varietal"+i);
                String vintage = savedInstanceState.getString("vintage"+i);
                Log.d("winename", name);
                WineData newWine = new WineData(name, region, price, winery, varietal, vintage);
                wineData.add(newWine);
            }
            recyclerView_adapter.notifyDataSetChanged();
        }

////        https://www.android-examples.com/android-recyclerview-with-gridview-gridlayoutmanager/ recycler view with grid layout

        Log.d("got here", "here");
        return view;
    }

    @Override
    public void onListItemClick(int position){
        Fragment fragment = new SpecificWine();
        WineData wine = wineData.get(position);
        String name = wine.name;
        String region = wine.region;
        String varietal = wine.varietal;
        String price = "$"+wine.price;
        String vintage = wine.vintage;
        String winery = wine.winery;



        ((SpecificWine) fragment).getText(name, region, price, varietal, vintage, winery);
        loadFragment(fragment);

    }

    private void loadFragment(Fragment fragment){
        if(fragment != null){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void getList(List<WineData> wine){
        wineData = wine;
    }


//    @Override
//    public void onSavedInstanceState(Bundle savedInstanceState){
//        super.onSaveInstanceState(savedInstanceState);
//        Log.d("saving", "saving size");
//        int size = wineData.size();
//        String ssize = Integer.toString(size);
//        Log.d("savesize", ssize);
//        savedInstanceState.putInt("size", wineData.size());
//        for (int i = 0; i < wineData.size(); i++){
//            WineData wine = wineData.get(i);
//            savedInstanceState.putString("name"+i, wine.name);
//            savedInstanceState.putString("region"+i, wine.region);
//            savedInstanceState.putString("price"+i, wine.price);
//            savedInstanceState.putString("winery"+i, wine.winery);
//            savedInstanceState.putString("varietal"+i, wine.varietal);
//            savedInstanceState.putString("vintage"+i, wine.vintage);
//        }
//    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("saving", "saving size");
        int size = wineData.size();
        String ssize = Integer.toString(size);
        Log.d("savesize", ssize);
        savedInstanceState.putInt("size", wineData.size());
        for (int i = 0; i < wineData.size(); i++){
            WineData wine = wineData.get(i);
            savedInstanceState.putString("name"+i, wine.name);
            savedInstanceState.putString("region"+i, wine.region);
            savedInstanceState.putString("price"+i, wine.price);
            savedInstanceState.putString("winery"+i, wine.winery);
            savedInstanceState.putString("varietal"+i, wine.varietal);
            savedInstanceState.putString("vintage"+i, wine.vintage);
        }

        super.onSaveInstanceState(savedInstanceState);


    }




}
