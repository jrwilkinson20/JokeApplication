package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The JokeActivity class is used to display the joke to the user. The joke is either retrieve from the API or from the built - in database.
 */

// Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Start of class
public class JokeActivity extends AppCompatActivity {

    // Instance variables
    public TextView view;
    private ShareActionProvider provider;
    public CoordinatorLayout layout;
    public TextView text;
    private String url;
    String urlCategory;
    String created;
    public CreateYourOwn dataSource;

    // Methods
    // Creates the class and connects it to its layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        JokeDatabaseHelper jokeDatabaseHelper = new JokeDatabaseHelper(this);
        Cursor cursor = jokeDatabaseHelper.getJoke();
        dataSource = new CreateYourOwn();
        // Find each variable and connect it to its respective layout
        view = findViewById(R.id.viewText);
        layout = findViewById(R.id.activity_joke);
        text = findViewById(R.id.jokeTextView);
        Intent intent = getIntent();
        // Find the cursor and move it to the first index
        if (!cursor.isAfterLast()) {
            cursor.moveToFirst();
            view.setText(cursor.getString(cursor.getColumnIndex(JokeDatabaseHelper.JOKE_COL)));
        }
        // Get the value of the button the user clicked and send it to the API
        created = intent.getStringExtra("created");
        urlCategory = intent.getStringExtra("button");
        url = "https://jokeapi.p.rapidapi.com/category/" + urlCategory;
        new FetchResults().execute(url);
        // When the user clicks the back button, bring them to the CategoryActivity screen
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Back?", Snackbar.LENGTH_LONG);
                startActivity(new Intent(JokeActivity.this, CategoryActivity.class));
            }
        });
    }

    // If the user clicks on the next joke button, retrieve a new joke
    public void onClick(View v) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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

    // Gets the results from the API
    private class FetchResults extends AsyncTask<String, Void, String> {

        // In the background, get an async task to retrieve the data
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String results;
            // Get the url that was declared in the instance variables
            try {
                URL url = new URL(params[0]);
                // Get a connection to the API
                urlConnection = (HttpURLConnection) url.openConnection();
                // Ask to get some information
                urlConnection.setRequestMethod("GET");
                // Key to prove the user is authentic
                urlConnection.setRequestProperty("X-RapidAPI-Key", "292bfbd148msha9961407c7c9467p1d51c2jsn68561fad6b2c");
                // Connect to the API
                urlConnection.connect();
                // If the result does not exist, do not return anything
                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }
                // Get the string from the buffer
                reader = new BufferedReader(new InputStreamReader(in));
                results = getStringFromBuffer(reader).toString();
                // If there's an error, report this message
            } catch (Exception e) {
                return null;
                // If the url does not exist, disconnect from the API
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                // If there's nothing in the reader, do not report anything
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        return null;
                    }
                }
            }
            return results;
        }

        // Execute this method to get the desired results
        protected void onPostExecute(String result) {
            // If there are results, print them
            if (result != null) {
                try {
                    // Set the results to the view
                    String displayString = new ResultsHandler().getJokeInfo(result);
                    view.setText(displayString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // If all - else fails, don't print anything
            } else {
            }
        }

        // Get the string from the buffer
        private StringBuffer getStringFromBuffer(BufferedReader bufferedReader) throws Exception {
            StringBuffer buffer = new StringBuffer();
            String line;

            // While the buffer is not empty, keep printing out information
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + '\n');
            }
            if (buffer.length() == 0) {
                return null;
            }
            return buffer;
        }
    }
}
