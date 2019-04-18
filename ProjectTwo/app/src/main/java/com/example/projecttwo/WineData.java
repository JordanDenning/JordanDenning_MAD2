package com.example.projecttwo;

public class WineData {
    public String name;
    public String region;
    public String price;
    public String winery;
    public String varietal;
    public String vintage;

    WineData(String name, String region, String price, String winery, String varietal, String vintage){
        this.name = name;
        this.region = region;
        this.price = price;
        this.winery = winery;
        this.varietal = varietal;
        this.vintage = vintage;
    }
}
