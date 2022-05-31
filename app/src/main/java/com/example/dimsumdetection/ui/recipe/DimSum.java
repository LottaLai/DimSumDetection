package com.example.dimsumdetection.ui.recipe;

public class DimSum {
    private int id;
    private String name;
    private String imageUrl;
    private String tag;
    private int recipeid;
    private int rating;

    public DimSum(int id, String name, String imageUrl, String tag, int recipeid, int rating){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.recipeid = recipeid;
        this.rating = rating;
    }

    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getImageUrl(){
        return imageUrl;
    }
    public String getTag(){
        return tag;
    }
    public int getRecipeid() { return recipeid; }
    public int getRating() { return rating; }
}
