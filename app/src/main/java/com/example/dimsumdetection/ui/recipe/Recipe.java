package com.example.dimsumdetection.ui.recipe;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private String tag;
    public Recipe(int id, String name, String description, String imageUrl, String tag){
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.tag = tag;
    }

    public int GetID(){
        return id;
    }
    public String GetName(){
        return name;
    }
    public String GetDescription(){
        return description;
    }
    public String GetImageUrl(){
        return imageUrl;
    }
    public String GetTag(){
        return tag;
    }
}
