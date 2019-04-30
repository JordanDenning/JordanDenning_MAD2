package com.example.projecttwo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavSpecificWine extends Fragment {

    String wineName;
    String wineRegion;
    String winePrice;
    String wineVarietal;
    String wineVintage;
    String wineWinery;
    TextView name;
    TextView region;
    TextView varietal;
    TextView vintage;
    TextView winery;
    TextView price;
    public FavSpecificWine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fav_specific_wine, container, false);
        name = view.findViewById(R.id.wineName);
        region = view.findViewById(R.id.wineRegion);
        varietal = view.findViewById(R.id.wineVarietal);
        vintage = view.findViewById(R.id.wineVintage);
        price = view.findViewById(R.id.winePrice);
        winery = view.findViewById(R.id.wineWinery);

        if(savedInstanceState == null){
            setText();
        }
        else{
            wineName = savedInstanceState.getString("name");
            wineRegion = savedInstanceState.getString("region");
            wineVarietal = savedInstanceState.getString("varietal");
            wineVintage = savedInstanceState.getString("vintage");
            wineWinery = savedInstanceState.getString("winery");
            winePrice = savedInstanceState.getString("price");
            setText();

        }

        return view;
    }

    public void getText(String name, String region, String price, String varietal,String vintage,String winery){
        wineName = name;
        wineRegion = region;
        winePrice = price;
        wineVarietal = varietal;
        wineVintage = vintage;
        wineWinery = winery;

    }



    public void setText(){
        name.setText(wineName);
        region.setText(wineRegion);
        varietal.setText(wineVarietal);
        vintage.setText(wineVintage);
        price.setText(winePrice);
        winery.setText(wineWinery);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("calling sis", "calling saved instance state");
        savedInstanceState.putString("name", wineName);
        savedInstanceState.putString("region", wineRegion);
        savedInstanceState.putString("price", winePrice);
        savedInstanceState.putString("winery", wineWinery);
        savedInstanceState.putString("varietal", wineVarietal);
        savedInstanceState.putString("vintage", wineVintage);



    }

}
