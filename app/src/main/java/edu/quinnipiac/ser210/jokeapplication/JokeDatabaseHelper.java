package edu.quinnipiac.ser210.jokeapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        String createTable = "CREATE TABLE " + JOKE_TABLE + "(" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JOKE_COL + " text not null);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + JOKE_TABLE);
        onCreate(db);
    }

    public void insertJoke(String joke) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues jokeValues = new ContentValues();
        jokeValues.put(JOKE_COL, joke);
        db.insert(DB_NAME, null, jokeValues);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE JOKES (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "JOKE TEXT);");
        }
    }

    public Cursor getJoke() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + DB_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

}
