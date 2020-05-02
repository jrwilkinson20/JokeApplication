package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The CreateYourOwn class allows the user to create their own joke and store it into the database.
 */

// Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

// Start of class
public class CreateYourOwn extends AppCompatActivity {

    // Instance variables
    private Button submit;
    private Button viewJoke;
    public EditText joke;
    private ShareActionProvider provider;
    public CoordinatorLayout layout;
    public TextView text, text1;
    public JokeDatabaseHelper jokeDatabaseHelper;
    private SQLiteDatabase database;
    private String[] allCols = {JokeDatabaseHelper.ID_COL, JokeDatabaseHelper.JOKE_COL};


    // Methods
    // Creates the class and connects it to its layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own);
        // Find each variable and connect it to its respective layout
        FloatingActionButton fab = findViewById(R.id.fab);
        layout = findViewById(R.id.activity_create_your_own);
        text = findViewById(R.id.createTextView);
        text1 = findViewById(R.id.instructionsTextView);
        submit = findViewById(R.id.submit);
        viewJoke = findViewById(R.id.viewJoke);
        joke = findViewById(R.id.userInput);
        CreatedJokes createdJokes = new CreatedJokes(joke.getText().toString());
        jokeDatabaseHelper = new JokeDatabaseHelper(this);
        jokeDatabaseHelper.insertJoke(createdJokes);
        database = jokeDatabaseHelper.getWritableDatabase();
        // When the user clicks the submit button, submit the joke to the database
        submit.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          String Joke = joke.getText().toString();
                                          addJoke(Joke);
                                          joke.setText(Joke);
                                      }
                                  }
        );

        // When the user clicks the view joke button, bring the user to the JokeActivity screen
        viewJoke.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(CreateYourOwn.this, JokeActivity.class);
                                            intent.putExtra("created", String.valueOf(joke));
                                            startActivity(intent);
                                        }
                                    }
        );
        // When the user clicks the back button, bring them to the CategoryActivity screen
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Back?", Snackbar.LENGTH_LONG);
                startActivity(new Intent(CreateYourOwn.this, CategoryActivity.class));
            }
        });
    }

    // Adds a joke to the database
    public CreatedJokes addJoke(String newJoke) {
        ContentValues jokes = new ContentValues();
        // Put the user - created joke into the database
        jokes.put(JokeDatabaseHelper.JOKE_COL, newJoke);
        long insertId = database.insert(JokeDatabaseHelper.JOKE_TABLE, null, jokes);
        // Allow the cursor to set the ID of the joke
        Cursor cursor = database.query(JokeDatabaseHelper.JOKE_TABLE, allCols, JokeDatabaseHelper.ID_COL + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        CreatedJokes createdJoke = cursorToJoke(cursor);
        cursor.close();
        return createdJoke;
    }

    // Sets the cursor to the desired joke
    private CreatedJokes cursorToJoke(Cursor cursor) {
        CreatedJokes createdJoke = new CreatedJokes("Display");
        createdJoke.setId(cursor.getLong(0));
        createdJoke.setJoke(cursor.getString(1));
        return createdJoke;
    }

    // Create the menu in the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getIntent();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        return true;
    }

    // Assign each variable to its respective function
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
}
