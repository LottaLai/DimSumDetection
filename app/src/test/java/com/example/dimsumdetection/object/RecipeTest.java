package com.example.dimsumdetection.object;

import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeTest {

    @Test
    public void getRecipeid() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        int result = recipe.getRecipeid();
        assertNotEquals(2, result);
    }

    @Test
    public void getName() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getName();
        assertNotEquals("", result);
    }

    @Test
    public void getEquipments() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getEquipments();
        assertEquals("ChopChop", result);
    }

    @Test
    public void getIngredients() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getIngredients();
        assertNotEquals("ChopChop", result);
    }

    @Test
    public void getImageUrl() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getImageUrl();
        assertEquals("https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", result);
    }

    @Test
    public void getVideoUrl() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getVideoUrl();
        assertNotEquals("https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", result);
    }

    @Test
    public void getSteps_1() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getSteps_1();
        assertEquals("1", result);
    }

    @Test
    public void getSteps_2() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getSteps_2();
        assertNotEquals("1", result);
    }

    @Test
    public void getSteps_3() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        String result = recipe.getSteps_3();
        assertEquals("3", result);
    }

    @Test
    public void getRestaurantid() {
        Recipe recipe = new Recipe(1, "Meat", "ChopChop", "200g salt", "https://cw1.tw/CH/images/channel_master/b116bb5f-4c7a-4a97-979b-3a46435248a0.jpg", "https://youtu.be/XmdxzXivbnU", "1", "2", "3", 1);
        int result = recipe.getRestaurantid();
        assertNotEquals(3, result);
    }
}