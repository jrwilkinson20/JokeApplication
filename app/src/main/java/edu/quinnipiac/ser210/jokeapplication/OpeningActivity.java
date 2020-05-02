package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The OpeningActivity class creates the opening screen of the app.
 */

// Imports
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

// Start of class
public class OpeningActivity extends AppCompatActivity {

    // Methods
    // Creates the class and connects it to its layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        // Set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // When the user clicks on the begin button, bring them to the CategoryActivity screen
    public void onBegin(View view) {
        Intent intent = new Intent(OpeningActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
}
    public void onBegin(View view) {
        Intent intent = new Intent(OpeningActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
}
