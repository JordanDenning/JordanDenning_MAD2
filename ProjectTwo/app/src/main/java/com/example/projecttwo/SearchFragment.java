package com.example.projecttwo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{
    List<WineData> wineData = new ArrayList<>();
    private RequestQueue queue;
    Context context;
    String color;
    String wineType;
    private static final String API_URL = "https://api.snooth.com/wines/?akey=65x5832y013kw1hmvytti3p4xkfott3skvw6e5agp110n5lw&n=20&";


    public SearchFragment() {
        // Required empty public constructor
    }
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_search, container, false);

        Button searchButton = (Button) view.findViewById(R.id.button);
        searchButton.setOnClickListener(this);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.redWineList, android.R.layout.simple_spinner_item);
        MultiStateToggleButton button = (MultiStateToggleButton) view.findViewById(R.id.mstb_multi_id);
        button.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int position) {
                if (position == 0){
//                    Log.d("Color: ", "Red");
                    color = "red";
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                            R.array.redWineList, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                }
                else if (position == 1){
//                    Log.d("Color: ", "white");
                    color = "white";
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                            R.array.whiteWineList, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }
                else if (position == 2){
//                    Log.d("Color: ", "Rose");
                    color = "rose";
                    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                            R.array.roseWineList, android.R.layout.simple_spinner_item);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }

            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
               wineType = parentView.getItemAtPosition(position).toString();
//               Log.d("wine type", wineType);

            }


        @Override
        public void onNothingSelected(AdapterView<?> parentView) {

        }
    });

        return view;
    }

    @Override
    public void onClick(View v){
        context = view.getContext();
        requestData(context);


    }


    private void loadFragment(Fragment fragment){
        if(fragment != null){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void requestData(Context context) {
        Log.d("data", "Data requested");
        queue = (RequestQueue) Volley.newRequestQueue(context);
        if (color == null)
        {
            color = "red";
        }
            String url = API_URL + "color=" + color + "&q=" + wineType;
            Log.d("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("parsing", "parsing data");
//                        Log.d("response", response);
                        parseJSON(response);



                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage(), error);
            }
        });
//        Log.d("hi", "hi");

        // Add the request to the RequestQueue
        queue.add(stringRequest);

    }

    private void parseJSON(String response){
        wineData.clear();
//        Log.d("parse", "Got to Parsing!");
        if (response == null){
            response = "THERE WAS AN ERROR";
            Log.d("error", response);
        }

        try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            JSONArray winearray = object.getJSONArray("wines");
            JSONObject hi= winearray.getJSONObject(0);
            for(int i = 0; i < winearray.length(); i++){
                JSONObject wineArray = winearray.getJSONObject(i);
                String name = wineArray.getString("name");
                String[] nameArray = name.split(" ");
                if (nameArray.length > 4){
                    name = nameArray[0] + " " + nameArray[1] +  " " + nameArray[2] + " " + nameArray[3];
                }

                String vintage = wineArray.getString("vintage");
                String varietal = wineArray.getString("varietal");
                String region = wineArray.getString("region");
                String price = wineArray.getString("price");
                String winery = wineArray.getString("winery");
                WineData newWine = new WineData(name, region,price, winery, varietal, vintage);
                wineData.add(newWine);
//                Log.d("myTag", newWine.name);

            }


        }
        catch (JSONException e){
            e.printStackTrace();
        }
        Fragment fragment = new LoadedWine();
        ((LoadedWine) fragment).getList(wineData);
        loadFragment(fragment);
    }




}
