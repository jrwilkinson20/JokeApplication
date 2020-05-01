package edu.quinnipiac.ser210.jokeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class JokeDatabaseHelper extends SQLiteOpenHelper {

    public static final String JOKE_TABLE = "jokes";
    public static final String DB_NAME = "created_jokes";
    public static final String JOKE_COL = "joke_column";
    public static final String ID_COL = "id_column";
    public static final int DB_VERSION = 1;

    public JokeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("SendHelp", "Send Help");
        String createTable = "CREATE TABLE " + JOKE_TABLE + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JOKE_COL + " text not null);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JOKE_TABLE);
        onCreate(db);
    }

    public void insertJoke(CreatedJokes joke) {
        Log.e("insertJoke", "Joke inserted");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues jokeValues = new ContentValues();

        jokeValues.put(JOKE_COL, joke.getJoke());
        db.insert(JOKE_TABLE, null, jokeValues);
        db.close();
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE JOKES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "JOKE TEXT);");
        }
    }

    public Cursor getJoke() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + JOKE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

}
