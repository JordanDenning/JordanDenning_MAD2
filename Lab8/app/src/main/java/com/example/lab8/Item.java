package com.example.lab8;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
//    private Boolean checked;
    private static final String PREFS_NAME = "ITEMS";

//    public Item (String newItem, Boolean check){
//         name = newItem;
//         checked = check;
//
//    }
    public Item (String newItem){
        name = newItem;

    }

    public static List<Item> myList = new ArrayList<Item>(){{
        add(new Item("App: Lab 8"));
    }};

    public String getName(){
        return name;

    }

//    public Boolean getBool(){
//        return checked;
//    }

    public static void storeData(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("size", myList.size());

        for(int i = 0; i < myList.size(); i++){
            editor.putString("item" + i, myList.get(i).getName());
//            editor.putBoolean("item" +i, myList.get(i).getBool());
        }

        editor.apply();
    }

    public static void loadData(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int size = sharedPref.getInt("size", 0);

        if(size>0){
            for (int i = 0; i<size; i++){
                String newName = sharedPref.getString("item" + i, "");
//                Boolean newBool = sharedPref.getBoolean("item" + i, true);
//                new Item(newName, newBool);
                new Item(newName);
            }
        }
        editor.apply();
    }

}


