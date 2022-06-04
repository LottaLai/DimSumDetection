package com.example.dimsumdetection.ui.recipe;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int restaurantid;
    private String name;
    private Location location;
    private int rating;
    private String imageurl;

    public Restaurant(int restaurantid, String name, Location location, int rating, String imageurl) {
        this.restaurantid = restaurantid;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.imageurl = imageurl;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getRating() {
        return rating;
    }

    public String getImageurl() {
        return imageurl;
    }
}

