package com.example.lab9;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    EditText nameText;
    TextView dayText;
    TextView monthText;
    TextView answerText;
    String name;
    nameData newdata = new nameData();
    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.button);
        nameText = findViewById(R.id.editText);
        answerText = findViewById(R.id.answerText);
        dayText = findViewById(R.id.dayText);
        monthText = findViewById(R.id.monthText);

        goButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //https://www.codingdemos.com/android-hide-keyboard-button-click/
                //get keyboard to go away on button click, next line of code
                answerText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                name = nameText.getText().toString();
                if(name.equals("Name") || name.equals("")){
                    answerText.setText("Please enter a name");
                    dayText.setText("");
                    monthText.setText("");
                }
                else {
                    Log.d("name", name);
                    requestData(name);
                }
            }
            });
        }




    private void requestData(final String name) {
        Log.d("data", "Data requested");
        queue = (RequestQueue) Volley.newRequestQueue(this);

        String url = "https://api.abalin.net/get/getdate?name="+name+"&calendar=us";
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
                answerText.setText("Sorry, there is no name day for this name.");
                dayText.setText("");
                monthText.setText("");

            }
        });
//        Log.d("hi", "hi");

        // Add the request to the RequestQueue
        queue.add(stringRequest);

    }

    private void parseJSON(String response) {
//        wineData.clear();
//        Log.d("parse", "Got to Parsing!");
        Integer day = 0;
        Integer month = 0;
        if (response == null) {
            response = "THERE WAS AN ERROR";
            Log.d("error", response);
        }

        try {
            JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
            JSONArray namedataArray = object.getJSONArray("results");
//            JSONObject hi= winearray.getJSONObject(0);
            if (namedataArray.length() == 0) {
                dayText.setText("no data available");
            }
            JSONObject nameData = namedataArray.getJSONObject(0);
            day = nameData.getInt("day");
            month = nameData.getInt("month");
            newdata = new nameData(name, day, month);

//                Log.d("myTag", newWine.name);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (day == 0) {
            answerText.setText("Sorry, there is no name day for the name " + name);
            dayText.setText("");
            monthText.setText("");
        } else {

            answerText.setText(name + " your name day is:");
            dayText.setText(Integer.toString(day));
            switch (month) {

                case 1:
                    monthText.setText("January");
                case 2:
                    monthText.setText("February");
                case 3:
                    monthText.setText("March");
                case 4:
                    monthText.setText("April");
                case 5:
                    monthText.setText("May");
                case 6:
                    monthText.setText("June");
                case 7:
                    monthText.setText("July");
                case 8:
                    monthText.setText("August");
                case 9:
                    monthText.setText("September");
                case 10:
                    monthText.setText("October");
                case 11:
                    monthText.setText("November");
                case 12:
                    monthText.setText("December");
                default:
                    monthText.setText("January");
            }

        }
    }


}
