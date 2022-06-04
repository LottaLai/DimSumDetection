package com.example.dimsumdetection.ui.recipe;

public class Recipe {
    private int id;
    private String name;
    private String imageUrl;
    public Recipe(int id, String name, String imageUrl){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int GetID(){
        return id;
    }
    public String GetName(){
        return name;
    }
    public String GetImageUrl(){
        return imageUrl;
    }
}
