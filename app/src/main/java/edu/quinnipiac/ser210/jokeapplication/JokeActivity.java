package edu.quinnipiac.ser210.jokeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Random;

public class JokeActivity extends AppCompatActivity {
    TextView view;
    private ShareActionProvider provider;
    public CoordinatorLayout layout;
    public TextView text;
    private String url;
    String urlCategory;
    private CreateYourOwn dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        dataSource = new CreateYourOwn();
//        dataSource.open();
        view = findViewById(R.id.viewText);
        layout = findViewById(R.id.activity_joke);
        text = (TextView) findViewById(R.id.jokeTextView);
        Intent intent = getIntent();
        SQLiteOpenHelper jokeDatabaseHelper = new JokeDatabaseHelper(this);
        urlCategory = intent.getStringExtra("button");
        url = "https://jokeapi.p.rapidapi.com/category/" + urlCategory;
        new FetchResults().execute(url);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Back?", Snackbar.LENGTH_LONG);
                startActivity(new Intent(JokeActivity.this, CategoryActivity.class));

            }
        });
    }



    //this is for a new joke
    public void onClick (View v){
        CreatedJokes joke = null;
        String[] jokes = new String[]{};
        int nextInt = new Random().nextInt(5);
//        joke = dataSource.addJoke(jokes[nextInt]);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        dataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // dataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getIntent();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem shareItem = menu.findItem(R.id.action_share);
        provider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
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



    private class FetchResults extends AsyncTask<String, Void, String> {
        ResultsHandler resultsHandler = new ResultsHandler();
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String results;
            // Try to get the url that was declared in the instance variables
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
                // If the results do not exist, do not return anything
                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }
                // Get the string from the buffer
                reader = new BufferedReader(new InputStreamReader(in));
                results = getStringFromBuffer(reader).toString();
                // If there's an error, report this message
            }catch(Exception e){
                return null;
                // If the url does not exist, disconnect from the API
            }finally{
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
        protected void onPostExecute (String result) {

            // If there are results, print them

            if (result != null) {
                try {
                    String displayString = new ResultsHandler().getJokeInfo(result);
                    Log.e("Result =", displayString);
                    view.setText(displayString);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            } else {
                Log.e("Error", "Result is null");
            }
        }

        // Get the string from the buffer
        private StringBuffer getStringFromBuffer (BufferedReader bufferedReader) throws Exception {
            StringBuffer buffer = new StringBuffer();
            String line;

            // While the buffer is not empty, keep printing out information
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + '\n');

            }

            if(buffer.length() == 0){
                return null;
            }
            return buffer;
        }



    }


}
