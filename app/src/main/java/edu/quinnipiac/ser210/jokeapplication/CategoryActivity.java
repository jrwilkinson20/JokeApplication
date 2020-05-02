package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The CategoryActivity class is used to display the five different options the user can select from in order to get a joke. Depending on what category
the user clicks, the class will send an intent to the JokeActivity in order to retrieve the desired joke.
 */

// Imports
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.ShareActionProvider;

// Start of class
public class CategoryActivity extends AppCompatActivity {

    // Instance variables
    boolean userSelect = false;
    private ShareActionProvider provider;
    public CoordinatorLayout layout;
    public TextView text;

    // Methods
    // Creates the class and connects it to its layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        // Find the layout and text so its color can be changed
        layout = findViewById(R.id.activity_category);
        text = findViewById(R.id.categoryTextView);
        FloatingActionButton fab = findViewById(R.id.fab);
        // Get the intent from the OpeningActivity class
        getIntent();
        // When the user clicks on the back button, bring them back to the OpeningActivity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Back?", Snackbar.LENGTH_LONG);
                startActivity(new Intent(CategoryActivity.this, OpeningActivity.class));
            }
        });

    }

    // Creates the options menu that is displayed on the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getIntent();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        return true;
    }

    // Connect each option to its respective function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
                // Settings function
            case R.id.action_settings:
                Toast.makeText(this, "You can change my background and text color here!", Toast.LENGTH_SHORT);
                return true;
                // Share function
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
                provider.setShareIntent(intent);
                return true;
                // Help function
            case R.id.action_help:
                AlertDialog.Builder help = new AlertDialog.Builder(this);
                help.setTitle("How to Use");
                help.setMessage("This app is used to display jokes for you! Click on any of the categories on the Categories screen in order for me to get a joke for you! If you want to make your own joke, click on the 'Create Your Own' Category!");
                help.setCancelable(true);
                help.show();
                return true;
                // Blue background option
            case R.id.blueBackground:
                layout.setBackgroundColor(Color.parseColor("#2c2d7d"));
                return true;
                // Black background option
            case R.id.blackBackground:
                layout.setBackgroundColor(Color.parseColor("#000000"));
                return true;
                // Green background option
            case R.id.greenBackground:
                layout.setBackgroundColor(Color.parseColor("#4bb458"));
                return true;
                // Red text option
            case R.id.redText:
                text.setTextColor(Color.parseColor("#ed1215"));
                return true;
                // White text option
            case R.id.whiteText:
                text.setTextColor(Color.parseColor("#ffffff"));
                return true;
                // Purple text option
            case R.id.purpleText:
                text.setTextColor(Color.parseColor("#a279e4"));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // Depending on what category is clicked, bring the value to the JokeActivity to retrieve the joke
    public void onClick(View view) {
        // Programming category button
        if (view.getId() == R.id.programmingButton) {
            Intent intent = new Intent(CategoryActivity.this, JokeActivity.class);
            intent.putExtra("button", "Programming");
            startActivity(intent);
            // Dark category button
        } else if (view.getId() == R.id.darkButton) {
            Intent intent = new Intent(CategoryActivity.this, JokeActivity.class);
            intent.putExtra("button", "Dark");
            startActivity(intent);
            // Miscellaneous category button
        } else if (view.getId() == R.id.miscButton) {
            Intent intent = new Intent(CategoryActivity.this, JokeActivity.class);
            intent.putExtra("button", "Miscellaneous");
            startActivity(intent);
            // Any category button
        } else if (view.getId() == R.id.anyButton) {
            Intent intent = new Intent(CategoryActivity.this, JokeActivity.class);
            intent.putExtra("button", "Any");
            startActivity(intent);
            // Create Your Own category button
        } else if (view.getId() == R.id.createButton) {
            Intent intent = new Intent(CategoryActivity.this, CreateYourOwn.class);
            intent.putExtra("button", "Create");
            startActivity(intent);
        }
    }

    // Keep track of the user's interaction with the action bar
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        userSelect = true;
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getIntent();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider)MenuItemCompat.getActionProvider(shareItem);
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
                Toast.makeText(this, "You can change my background and text color here!", Toast.LENGTH_SHORT);
                return true;
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "This is a message for you");
                provider.setShareIntent(intent);
                return true;
            case R.id.action_help:
                AlertDialog.Builder help = new AlertDialog.Builder(this);
                help.setTitle("How to Use");
                help.setMessage("This app is used to display jokes for you! Click on any of the categories on the Categories screen in order for me to get a joke for you! If you want to make your own joke, click on the 'Create Your Own' Category!");
                help.setCancelable(true);
                help.show();
                return true;
            case R.id.blueBackground:
                layout.setBackgroundColor(Color.parseColor("#2c2d7d"));
                return true;
            case R.id.blackBackground:
                layout.setBackgroundColor(Color.parseColor("#000000"));
                return true;
            case R.id.greenBackground:
                layout.setBackgroundColor(Color.parseColor("#4bb458"));
                return true;
            case R.id.redText:
                 text.setTextColor(Color.parseColor("#ed1215"));
                 return true;
            case R.id.whiteText:
                text.setTextColor(Color.parseColor("#ffffff"));
                return true;
            case R.id.purpleText:
                text.setTextColor(Color.parseColor("#a279e4"));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //This is what happens when each button is clicked.
    public void onClick(View view) {
        if (view.getId() == R.id.programmingbutton) {
            Intent intent = new Intent(CategoryActivity.this, JokeActivity.class);
            intent.putExtra("button", "Programming");
            startActivity(intent);
        } else if(view.getId() == R.id.darkbutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "Dark");
            startActivity(intent);
        }else if(view.getId() == R.id.miscbutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "Miscellaneous");
            startActivity(intent);
        }else if(view.getId() == R.id.anybutton){
            Intent intent = new Intent(CategoryActivity.this,JokeActivity.class);
            intent.putExtra("button", "Any");
            startActivity(intent);
        }else if(view.getId() == R.id.createbutton){
            Intent intent = new Intent(CategoryActivity.this,CreateYourOwn.class);
            intent.putExtra("button", "Create");
            startActivity(intent);

        }
    }



    @Override
    public void onUserInteraction(){
        super.onUserInteraction();
        userSelect = true;
    }
}
