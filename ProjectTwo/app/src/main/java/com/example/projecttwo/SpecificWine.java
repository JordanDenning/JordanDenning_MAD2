package com.example.projecttwo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
TextView name;
TextView region;
TextView varietal;
TextView vintage;
TextView winery;
TextView price;
DatabaseReference wineRef = FirebaseDatabase.getInstance().getReference("Wine");
    public SpecificWine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_specific_wine, container, false);
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


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //create a vertical linear layout to hold edit texts
//                LinearLayout layout = new LinearLayout(RecipeMainActivity.this);
//                layout.setOrientation(LinearLayout.VERTICAL);

                //create edit texts and add to layout
//                final EditText nameEditText = new EditText(RecipeMainActivity.this);
//                nameEditText.setHint("Recipe name");
//                layout.addView(nameEditText);
//                final EditText urlEditText = new EditText(RecipeMainActivity.this);
//                urlEditText.setHint("URL");
//                layout.addView(urlEditText);

                //create alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Add Wine to Favorites?");
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //get entered data

                            //get new id from firebase
                            String key = wineRef.push().getKey();
                            //create new recipe item
                            FavWineData newWine = new FavWineData(key, wineName, wineRegion, winePrice, wineWinery, wineVarietal, wineVintage);
                            //add to Firebase
                            wineRef.child(key).child("name").setValue(newWine.getName());
                            wineRef.child(key).child("price").setValue(newWine.getPrice());
                            wineRef.child(key).child("region").setValue(newWine.getRegion());
                            wineRef.child(key).child("varietal").setValue(newWine.getVarietal());
                            wineRef.child(key).child("vintage").setValue(newWine.getVintage());
                            wineRef.child(key).child("winery").setValue(newWine.getWinery());
                        Snackbar.make(view, "Wine Added!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();


                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.show();
            }
        });
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
        savedInstanceState.putString("name", wineName);
        savedInstanceState.putString("region", wineRegion);
        savedInstanceState.putString("price", winePrice);
        savedInstanceState.putString("winery", wineWinery);
        savedInstanceState.putString("varietal", wineVarietal);
        savedInstanceState.putString("vintage", wineVintage);

        super.onSaveInstanceState(savedInstanceState);


    }


}
