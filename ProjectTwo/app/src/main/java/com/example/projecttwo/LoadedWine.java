package com.example.projecttwo;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    List<WineData> wineData;
    private static final String API_URL = "https://api.snooth.com/wines/?akey=65x5832y013kw1hmvytti3p4xkfott3skvw6e5agp110n5lw&n=20&color=red";

//     String[] colors = {"Red", "White", "Rose", "Purple", "Blue", "Teal", "Black", "Yellow", "Orange", "Green"};



    public LoadedWine() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_loaded_wine, container, false);
        context = view.getContext();
//        new RetrieveFeedTask().execute();

////        https://www.android-examples.com/android-recyclerview-with-gridview-gridlayoutmanager/ recycler view with grid layout
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView_adapter = new RecyclerViewAdapter(context, wineData, this);
        recyclerView.setAdapter(recyclerView_adapter);
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




}
