package com.example.projecttwo;

public class FavWineData {
    public String id;
    public String name;
    public String region;
    public String price;
    public String winery;
    public String varietal;
    public String vintage;

    public FavWineData(){

    }

    FavWineData(String id, String name, String region, String price, String winery, String varietal, String vintage){
        this.id = id;
        this.name = name;
        this.region = region;
        this.price = price;
        this.winery = winery;
        this.varietal = varietal;
        this.vintage = vintage;
    }

    public String getId() {
        return id;
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
