package edu.quinnipiac.ser210.jokeapplication;

/*
 * Authors: Julia Wilkinson, Victoria Gorski, Jenna Saleh
 * This is an app designed to allow a user to choose a random joke from a selected category,
 * or to create their own joke and save it to a database.
 * April 9, 2020
 * SER210
 */
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }


    public void onBegin(View view) {
        Intent intent = new Intent(OpeningActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
}