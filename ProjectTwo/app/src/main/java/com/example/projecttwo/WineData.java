package com.example.projecttwo;

import android.os.Parcelable;

public class WineData{
    public String name;
    public String region;
    public String price;
    public String winery;
    public String varietal;
    public String vintage;

    public WineData(){

    }

    WineData(String name, String region, String price, String winery, String varietal, String vintage){
        this.name = name;
        this.region = region;
        this.price = price;
        this.winery = winery;
        this.varietal = varietal;
        this.vintage = vintage;
    }

    public String getName(){
        return name;
    }

    public String getRegion(){
        return region;
    }

    public String getPrice(){
        return price;
    }

    public String getWinery(){
        return winery;
    }

    public String getVarietal(){
        return varietal;
    }

    public String getVintage(){
        return vintage;
    }
}
