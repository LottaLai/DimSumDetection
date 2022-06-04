package com.example.dimsumdetection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dimsumdetection.database.PostgreSQL;
import com.example.dimsumdetection.ui.recipe.Recipe;
import com.example.dimsumdetection.ui.recipe.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

public class RecipeDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textName;
    private TextView text_ingredients;
    private ImageView image_location;
    private TextView text_restaurant;
    private TextView text_rating;
    private TextView text_Instructions;
    private TextView text_cooking_step;

    private Recipe recipe;
    private Restaurant restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datail_recipe);

        imageView = findViewById(R.id.imageView);
        textName = findViewById(R.id.textName);
        text_ingredients = findViewById(R.id.text_ingredients);
        image_location = findViewById(R.id.image_location);
        text_restaurant = findViewById(R.id.text_restaurant);
        text_rating = findViewById(R.id.text_rating);
        text_Instructions = findViewById(R.id.text_Instructions);
        text_cooking_step = findViewById(R.id.text_cooking_step);

        Intent intent = getIntent();
        int recipeid = intent.getExtras().getInt("RecipeID");

        try {
            PostgreSQL postgreSQL = new PostgreSQL();
            Thread thread1 = new Thread(postgreSQL.SelectRecipe(recipeid));
            thread1.start();
            TimeUnit.SECONDS.sleep(1);
            recipe = postgreSQL.GetRecipe();

            Thread thread2 = new Thread(postgreSQL.SelectRestaurant(recipe.getRestaurantid()));
            thread2.start();
            TimeUnit.SECONDS.sleep(1);
            restaurant = postgreSQL.GetRestaurant();

            Picasso.get().load(recipe.getImageUrl()).into(imageView);
            textName.setText(recipe.getName());
            text_ingredients.setText(recipe.getIngredients());
            text_Instructions.setText(recipe.getSteps_1());
            text_cooking_step.setText(recipe.getSteps_2() + "\n\n" + recipe.getSteps_3());

            text_rating.setText("Rating : " + restaurant.getRating());
            text_restaurant.setText(restaurant.getName());

            image_location.setOnClickListener(v->{
                Intent location = new Intent(this, RestaurantActivity.class);
                location.putExtra("Name", restaurant.getName());
                location.putExtra("Latitude", restaurant.getLocation().getLatitude());
                location.putExtra("Longitude", restaurant.getLocation().getLongitude());
                startActivity(location);
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
