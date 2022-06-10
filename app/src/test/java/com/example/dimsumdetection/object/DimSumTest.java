package com.example.dimsumdetection.object;

import org.junit.Test;

import static org.junit.Assert.*;

public class DimSumTest {

    @Test
    public void getID() {
        DimSum dimSum = new DimSum(1, "Siu Mai", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Siomay_in_Cantonese_restaurant.jpg", "Siu Mai", 1, 4);
        int result = dimSum.getID();
        assertEquals(1, result);
    }

    @Test
    public void getName() {
        DimSum dimSum = new DimSum(1, "Siu Mai", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Siomay_in_Cantonese_restaurant.jpg", "Siu Mai", 1, 4);
        String result = dimSum.getName();
        assertEquals("Siu Mai", result);
    }

    @Test
    public void getImageUrl() {
        DimSum dimSum = new DimSum(1, "Siu Mai", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Siomay_in_Cantonese_restaurant.jpg", "Siu Mai", 1, 4);
        String result = dimSum.getImageUrl();
        assertNotEquals("", result);
    }

    @Test
    public void getTag() {
        DimSum dimSum = new DimSum(1, "Siu Mai", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Siomay_in_Cantonese_restaurant.jpg", "Siu Mai", 1, 4);
        String result = dimSum.getTag();
        assertNotEquals("", result);
    }

    @Test
    public void getRecipeId() {
        DimSum dimSum = new DimSum(1, "Siu Mai", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Siomay_in_Cantonese_restaurant.jpg", "Siu Mai", 1, 4);
        int result = dimSum.getRecipeId();
        assertEquals(1, result);
    }

    @Test
    public void getRating() {
        DimSum dimSum = new DimSum(1, "Siu Mai", "https://upload.wikimedia.org/wikipedia/commons/8/8a/Siomay_in_Cantonese_restaurant.jpg", "Siu Mai", 1, 4);
        int result = dimSum.getRating();
        assertNotEquals(1, result);
    }
}