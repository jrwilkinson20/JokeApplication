package edu.quinnipiac.ser210.jokeapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.MenuItemCompat;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.ShareActionProvider;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    boolean userSelect = false;
    private ArrayList<Button> buttons;
    private ShareActionProvider provider;
    public CoordinatorLayout layout;
    public TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        layout = findViewById(R.id.activity_category);
        text = (TextView) findViewById(R.id.categoryTextView);
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
