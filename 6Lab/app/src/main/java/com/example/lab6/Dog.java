package com.example.lab6;

import java.util.ArrayList;
import java.util.List;

public class Dog {
    private String name;
    private int imageResourceID;


    private Dog(String newName, int newID) {
        this.name = newName;
        this.imageResourceID = newID;
    }

    public static List<Dog> breeds = new ArrayList<Dog>() {{
        add(new Dog("American Terrior", R.drawable.americanterrior));
        add(new Dog("Australian Shepard", R.drawable.australianshepard));
        add(new Dog("Bernese Mountain Dog",R.drawable.bernesemountain));
        add(new Dog("German Shepard", R.drawable.germanshepard));
        add(new Dog("Golden Retriever", R.drawable.goldenretriever));
        add(new Dog("Siberian Husky", R.drawable.siberianhusky));
    }};

    public String getName() {
        return name;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public String toString(){
        return  this.name;
    }
}