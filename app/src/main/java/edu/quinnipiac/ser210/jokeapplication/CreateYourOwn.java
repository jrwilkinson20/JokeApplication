package edu.quinnipiac.ser210.jokeapplication;

import androidx.annotation.ColorInt;
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
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CreateYourOwn extends AppCompatActivity {

    TextView view;
    private Button submit;
    private Button viewJoke;
    private EditText joke;
    private EditText punchline;
    private ShareActionProvider provider;
    public CoordinatorLayout layout;
    public TextView text, text1;
    public JokeDatabaseHelper jokeDatabaseHelper;
    private SQLiteDatabase database;
    private String[] allCols = {JokeDatabaseHelper.ID_COL, JokeDatabaseHelper.JOKE_COL};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_own);
        FloatingActionButton fab = findViewById(R.id.fab);
        layout = findViewById(R.id.activity_create_your_own);
        text = (TextView) findViewById(R.id.createTextView);
        text1 = (TextView) findViewById(R.id.instructionsTextView);
        submit = (Button) findViewById(R.id.button2);
        viewJoke = (Button) findViewById(R.id.button3);
        joke = (EditText) findViewById(R.id.editText);
        punchline = (EditText) findViewById(R.id.editText2);

        jokeDatabaseHelper = new JokeDatabaseHelper(this);
        database = jokeDatabaseHelper.getWritableDatabase();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Joke = joke.getText().toString();
                addJoke(Joke);
                joke.setText(Joke);
            }
        }
        );

        viewJoke.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(CreateYourOwn.this, JokeActivity.class);
                                          //intent.putExtra(intent.EXTRA_TEXT, newJoke);
                                          startActivity(intent);
                                      }
                                  }
        );

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Back?", Snackbar.LENGTH_LONG);
                startActivity(new Intent(CreateYourOwn.this, CategoryActivity.class));
            }
        });


    }

    public void open() throws SQLiteException {
        database = jokeDatabaseHelper.getWritableDatabase();
    }


    public SQLiteDatabase getJokeFromDatabase() {
        return database;
}

public void close() {
        jokeDatabaseHelper.close();
}

    public CreatedJokes addJoke(String newJoke) {
       // Log.e(newJoke, "here is the joke");
        ContentValues jokes = new ContentValues();
        jokes.put(JokeDatabaseHelper.JOKE_COL, newJoke);
        long insertId = database.insert(JokeDatabaseHelper.JOKE_TABLE, null, jokes);
        Cursor cursor = database.query(JokeDatabaseHelper.JOKE_TABLE, allCols, JokeDatabaseHelper.ID_COL + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        CreatedJokes createdJoke = cursorToJoke(cursor);

      //  Log.e(createdJoke.toString(), "Joke is created");
        cursor.close();
        return createdJoke;
    }

    private CreatedJokes cursorToJoke(Cursor cursor) {
        CreatedJokes createdJoke = new CreatedJokes();
        createdJoke.setId(cursor.getLong(0));
        createdJoke.setJoke(cursor.getString(1));
        return createdJoke;

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
                text1.setTextColor(Color.parseColor("#ed1215"));
                return true;
            case R.id.whiteText:
                text.setTextColor(Color.parseColor("#ffffff"));
                text1.setTextColor(Color.parseColor("#ffffff"));
                return true;
            case R.id.purpleText:
                text.setTextColor(Color.parseColor("#a279e4"));
                text1.setTextColor(Color.parseColor("#a279e4"));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

