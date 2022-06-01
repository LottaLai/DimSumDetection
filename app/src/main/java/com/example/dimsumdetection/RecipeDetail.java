package com.example.dimsumdetection;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.ui.recipe.Recipe;

import java.util.concurrent.TimeUnit;

public class RecipeDetail extends AppCompatActivity {
    private TextView name;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datail_recipe);

        name = findViewById(R.id.textName);

        Intent intent = getIntent();
        int recipeid = intent.getExtras().getInt("RecipeID");

        try {
            PostgreSQL postgreSQL = new PostgreSQL();
            Thread thread = new Thread(postgreSQL.Select(recipeid));
            thread.start();
            TimeUnit.SECONDS.sleep(1);

            Recipe recipe = postgreSQL.GetRecipe();

            System.out.println(recipe.GetName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
