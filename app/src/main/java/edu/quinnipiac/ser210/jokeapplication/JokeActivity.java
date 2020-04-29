package edu.quinnipiac.ser210.jokeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JokeActivity extends AppCompatActivity {
    TextView view;
    private String url;
    String urlCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        view = findViewById(R.id.viewText);
        Intent intent = getIntent();
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
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    private class FetchResults extends AsyncTask<String, Void, String> {
        ResultsHandler resultsHandler = new ResultsHandler();
        // In the background, grab the url and the results of the selected game
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
                        //Log.e(LOG_TAG, "Error" + e.getMessage());
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
                    view.setText(displayString);
                    view.setTextColor(Color.rgb(221,160,221));
                }catch(JSONException e){
                    e.printStackTrace();
                }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}