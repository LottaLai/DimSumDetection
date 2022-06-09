package com.example.dimsumdetection.object;

public class Recipe {
    private int recipeid;
    private String name;
    private String equipments;
    private String ingredients;
    private String imageUrl;
    private String videoUrl;
    private String steps_1;
    private String steps_2;
    private String steps_3;
    private int restaurantid;

    public Recipe(int recipeid, String name, String equipments, String ingredients, String imageUrl, String videoUrl, String steps_1, String steps_2, String steps_3, int restaurantid) {
        this.recipeid = recipeid;
        this.name = name;
        this.equipments = equipments;
        this.ingredients = ingredients;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.steps_1 = steps_1;
        this.steps_2 = steps_2;
        this.steps_3 = steps_3;
        this.restaurantid = restaurantid;
    }

    public int getRecipeid() {
        return recipeid;
    }

    public String getName() {
        return name;
    }

    public String getEquipments() {
        return equipments;
    }

    public String getIngredients() {
        return ingredients.replace("\\n", System.getProperty("line.separator"));
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getSteps_1() {
        return steps_1;
    }

    public String getSteps_2() {
        return steps_2;
    }

    public String getSteps_3() {
        return steps_3;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

}
