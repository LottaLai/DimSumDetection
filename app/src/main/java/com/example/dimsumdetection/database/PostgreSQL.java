package com.example.dimsumdetection.database;

import android.content.Context;

import com.example.dimsumdetection.object.DimSum;
import com.example.dimsumdetection.object.Location;
import com.example.dimsumdetection.object.Recipe;
import com.example.dimsumdetection.object.Restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreSQL {
    private Context context;
    private final int port = 5432;
    private final String host = "tiny.db.elephantsql.com";
    private final String username = "bkijihke";
    private final String password = "1YK4vlCYgZlI8sRWJ_ZMjWquxsLhQyhb";
    private String url = "jdbc:postgresql://%s:%d/%s";

    //Connect status
    private volatile boolean finishFlag;

    private ArrayList<DimSum> dimsums = new ArrayList<DimSum>();
    private Recipe recipe;
    private Restaurant restaurant;

    public PostgreSQL(Context context){
        this.context = context;
    }

    public Thread SelectRestaurant(int restaurantid){
        return new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    Class.forName("org.postgresql.Driver");
                }catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    url = String.format(url, host, port, username);
                    Connection db = DriverManager.getConnection(url, username, password);
                    String query = "select * from public.restaurant where restaurantid = ?";
                    PreparedStatement pstmt = db.prepareStatement(query);
                    pstmt.setInt(1, restaurantid);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        int restaurantid = rs.getInt("restaurantid");
                        String name = rs.getString("name");
                        String imageUrl = rs.getString("imageurl");
                        Location location = new Location(rs.getDouble("latitude"), rs.getDouble("longitude"));
                        int rating = rs.getInt("rating");
                        restaurant = new Restaurant(restaurantid, name, location, rating, imageUrl);
                    }
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
                finishFlag = true;

                synchronized (this) {
                    this.notify();
                }
            }
        });
    }

    public Restaurant GetRestaurant(){
        synchronized (this) {
            if (!finishFlag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return restaurant;
    }

    public Thread SelectRecipe(int reciepeid){
        return new Thread(new Runnable() {
            @Override
            public void run(){
                try{
                    Class.forName("org.postgresql.Driver");
                }catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    url = String.format(url, host, port, username);
                    Connection db = DriverManager.getConnection(url, username, password);
                    String query = "select * from public.recipe where recipeid = ?";
                    PreparedStatement pstmt = db.prepareStatement(query);
                    pstmt.setInt(1, reciepeid);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        int recipeid = rs.getInt("recipeid");
                        String name = rs.getString("name");
                        String equipments = rs.getString("equipments");
                        String ingredients = rs.getString("ingredients");
                        String imageUrl = rs.getString("imageurl");
                        String videoUrl = rs.getString("videourl");
                        String steps_1 = rs.getString("steps_1");
                        String steps_2 = rs.getString("steps_2");
                        String steps_3 = rs.getString("steps_3");
                        int restaurantid = rs.getInt("restaurantid");
                        recipe = new Recipe(recipeid, name, equipments, ingredients, imageUrl, videoUrl, steps_1, steps_2, steps_3, restaurantid);
                    }
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
                finishFlag = true;

                synchronized (this) {
                    this.notify();
                }
            }
        });
    }

    public Recipe GetRecipe(){
        synchronized (this) {
            if (!finishFlag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return recipe;
    }

    public Thread SelectAllDimSum(){
        return new Thread(new Runnable(){
            @Override
            public void run(){
               try{
                   Class.forName("org.postgresql.Driver");
               }catch (ClassNotFoundException e) {
                   System.out.println(e.getMessage());
               }

               try {
                   url = String.format(url, host, port, username);
                   Connection db = DriverManager.getConnection(url, username, password);
                   String query = "select * from public.dimsum";
                   Statement stmt = db.createStatement();
                   ResultSet rs = stmt.executeQuery(query);

                   while (rs.next()) {
                       int id = rs.getInt("id");
                       String name = rs.getString("name");
                       String imageUrl = rs.getString("imageurl");
                       String tag = rs.getString("tag");
                       int recipeid = rs.getInt("recipeid");
                       int rating = rs.getInt("rating");
                       dimsums.add(new DimSum(id, name, imageUrl, tag, recipeid, rating ));
                   }
               } catch (Exception e) {
                   System.out.print(e.getMessage());
                   e.printStackTrace();
               }
               finishFlag = true;

               synchronized (this) {
                   this.notify();
               }
           }
        });
    }

    public Thread SelectDimSum(String tag){
        return new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Class.forName("org.postgresql.Driver");
                }catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }

                try {
                    url = String.format(url, host, port, username);
                    Connection db = DriverManager.getConnection(url, username, password);
                    String query = "select * from public.dimsum where tag = ?";
                    PreparedStatement pstmt = db.prepareStatement(query);
                    pstmt.setString(1, tag);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String imageUrl = rs.getString("imageurl");
                        String tag = rs.getString("tag");
                        int recipeid = rs.getInt("recipeid");
                        int rating = rs.getInt("rating");
                        dimsums.add(new DimSum(id, name, imageUrl, tag, recipeid, rating ));
                    }
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
                finishFlag = true;

                synchronized (this) {
                    this.notify();
                }
            }
        });
    }

    public ArrayList<DimSum> GetDimSums(){
        synchronized (this) {
            if (!finishFlag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return dimsums;
    }
}
