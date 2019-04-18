package com.example.projecttwo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecificWine extends Fragment {

String wineName;
String wineRegion;
String winePrice;
String wineVarietal;
String wineVintage;
String wineWinery;
    public SpecificWine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_specific_wine, container, false);
         setText(view);
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

    public void setText(View view){
        TextView name = view.findViewById(R.id.wineName);
        TextView region = view.findViewById(R.id.wineRegion);
        TextView varietal = view.findViewById(R.id.wineVarietal);
        TextView vintage = view.findViewById(R.id.wineVintage);
        TextView price = view.findViewById(R.id.winePrice);
        TextView winery = view.findViewById(R.id.wineWinery);
        name.setText(wineName);
        region.setText(wineRegion);
        varietal.setText(wineVarietal);
        vintage.setText(wineVintage);
        price.setText(winePrice);
        winery.setText(wineWinery);
;
    }


}
