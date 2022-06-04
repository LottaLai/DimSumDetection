package com.example.dimsumdetection.database;

import com.example.dimsumdetection.ui.recipe.DimSum;
import com.example.dimsumdetection.ui.recipe.Recipe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreSQL {
    private Connection db = null;
    private final int port = 5432;
    private final String host = "tiny.db.elephantsql.com";
    private final String username = "bkijihke";
    private final String password = "1YK4vlCYgZlI8sRWJ_ZMjWquxsLhQyhb";
    private String url = "jdbc:postgresql://%s:%d/%s";

    //Connect status
    private volatile boolean finishFlag;

    private ArrayList<DimSum> dimsums = new ArrayList<DimSum>();
    private Recipe recipe;

    public Thread Select(int reciepeid){
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
                    db = DriverManager.getConnection(url, username, password);
                    String query = "select * from public.recipe where recipeid = ?";
                    PreparedStatement pstmt = db.prepareStatement(query);
                    pstmt.setInt(1, reciepeid);
                    ResultSet rs = pstmt.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("recipeid");
                        String name = rs.getString("name");
                        String imageUrl = rs.getString("imageurl");
                        recipe = new Recipe(id, name, imageUrl);
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

    public Thread SelectAll(){
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
                   db = DriverManager.getConnection(url, username, password);
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
