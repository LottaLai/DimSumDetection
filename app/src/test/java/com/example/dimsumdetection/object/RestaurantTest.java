package com.example.dimsumdetection.object;

import org.junit.Test;

import static org.junit.Assert.*;

public class RestaurantTest {

    @Test
    public void getRestaurantid() {
        Restaurant restaurant = new Restaurant(1, "Mcdonald", new Location(20, 19), 2, "https://www.mcdonalds.com.hk/wp-content/uploads/2021/11/01MCD21090252_2021-Signature_Digital_HomePage_Promotion-SubBanner_Burger_585x340H.jpg");
        int result = restaurant.getRestaurantid();
        assertEquals(1, result);
    }

    @Test
    public void getName() {
        Restaurant restaurant = new Restaurant(1, "Mcdonald", new Location(20, 19), 2, "https://www.mcdonalds.com.hk/wp-content/uploads/2021/11/01MCD21090252_2021-Signature_Digital_HomePage_Promotion-SubBanner_Burger_585x340H.jpg");
        String result = restaurant.getName();
        assertNotEquals("None", result);
    }

    @Test
    public void getLocation() {
        Restaurant restaurant = new Restaurant(1, "Mcdonald", new Location(20, 19), 2, "https://www.mcdonalds.com.hk/wp-content/uploads/2021/11/01MCD21090252_2021-Signature_Digital_HomePage_Promotion-SubBanner_Burger_585x340H.jpg");
        Location result = restaurant.getLocation();
        assertEquals(new Location(20, 19), result);
    }

    @Test
    public void getRating() {
        Restaurant restaurant = new Restaurant(1, "Mcdonald", new Location(20, 19), 2, "https://www.mcdonalds.com.hk/wp-content/uploads/2021/11/01MCD21090252_2021-Signature_Digital_HomePage_Promotion-SubBanner_Burger_585x340H.jpg");
        int result = restaurant.getRating();
        assertNotEquals(1, result);
    }

    @Test
    public void getImageurl() {
        Restaurant restaurant = new Restaurant(1, "Mcdonald", new Location(20, 19), 2, "https://www.mcdonalds.com.hk/wp-content/uploads/2021/11/01MCD21090252_2021-Signature_Digital_HomePage_Promotion-SubBanner_Burger_585x340H.jpg");
        String result = restaurant.getImageurl();
        assertEquals(1, result);
    }
}