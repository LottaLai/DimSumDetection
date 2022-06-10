package com.example.dimsumdetection.object;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void getLongitude() {
        Location location = new Location(21, 25);
        double result = location.getLongitude();
        assertEquals(21, result);
    }

    @Test
    public void getLatitude() {
        Location location = new Location(21, 25);
        double result = location.getLatitude();
        assertNotEquals(21, result);
    }
}