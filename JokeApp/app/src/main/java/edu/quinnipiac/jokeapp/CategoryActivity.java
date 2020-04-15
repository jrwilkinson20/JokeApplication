package edu.quinnipiac.jokeapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    boolean userSelect = false;
    private ArrayList<Button> buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getIntent();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Back?", Snackbar.LENGTH_LONG);
                startActivity(new Intent(CategoryActivity.this,MainActivity.class));

            }
        });

    }
    //This is what happens when each button is clicked.
    public void onClick(View view) {
        if (view.getId() == R.id.programmingbutton) {
            Intent intent = new Intent(CategoryActivity.this, JokeActivity.class);
            intent.putExtra("button", "programming");
            startActivity(intent);
        } else if(view.getId() == R.id.darkbutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "dark");
            startActivity(intent);
        }else if(view.getId() == R.id.miscbutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "miscellaneous");
            startActivity(intent);
        }else if(view.getId() == R.id.anybutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "any");
            startActivity(intent);
        }else if(view.getId() == R.id.createbutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "create");
            startActivity(intent);

        }
    }
    @Override
    public void onUserInteraction(){
        super.onUserInteraction();
        userSelect = true;
    }
}
