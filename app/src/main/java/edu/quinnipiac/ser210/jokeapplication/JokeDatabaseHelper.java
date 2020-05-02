package edu.quinnipiac.ser210.jokeapplication;

/*
@authors: Victoria Gorski, Jenna Saleh, and Julia Wilkinson
@date: 5 - 2 - 20
@description: The JokeDatabaseHelper class directs the user - created joke into the database.
 */

// Imports
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Start of class
class JokeDatabaseHelper extends SQLiteOpenHelper {

    // Instance variables
    public static final String JOKE_TABLE = "jokes";
    public static final String DB_NAME = "created_jokes";
    public static final String JOKE_COL = "joke_column";
    public static final String ID_COL = "id_column";
    public static final int DB_VERSION = 1;

    // Methods
    // Creates the database
    public JokeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Creates the table stored in the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + JOKE_TABLE + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JOKE_COL + " text not null);";
        db.execSQL(createTable);
    }

    // If the user wants to insert a new joke, delete the current database and create a new one with the updated information
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JOKE_TABLE);
        onCreate(db);
    }

    // Inserts a joke into the database
    public void insertJoke(CreatedJokes joke) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues jokeValues = new ContentValues();
        // Put the user's joke into the joke column in the joke table
        jokeValues.put(JOKE_COL, joke.getJoke());
        db.insert(JOKE_TABLE, null, jokeValues);
        db.close();
    }

    // Gets the joke from the database and allows the cursor to place it in the JokeActivity screen
    public Cursor getJoke() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Select the desired joke from the query
        String query = "SELECT * FROM " + JOKE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
