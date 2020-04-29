package edu.quinnipiac.ser210.jokeapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
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
                startActivity(new Intent(CategoryActivity.this, OpeningActivity.class));

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
            Intent intent = new Intent(CategoryActivity.this,CreateYourOwn.class);
            intent.putExtra("button", "create");
            startActivity(intent);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getIntent();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserInteraction(){
        super.onUserInteraction();
        userSelect = true;
    }
}