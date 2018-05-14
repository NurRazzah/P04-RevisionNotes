package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

	//TODO Define the Database properties
	private static final String DATABASE_NAME = "";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NOTE = "Notes";
	private static final String COL_ID = "id";
	private static final String NOTE = "notecontent";
	private static final String RATING = "stars";




	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTable = "CREATE TABLE " + TABLE_NOTE +  " ( "
				+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ NOTE + " TEXT, "
				+ RATING+ " INTEGER ) ";
		Log.i("info" ,createTable);
		db.execSQL(createTable);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
		onCreate(db);
	}

	public void insertNote(String noteContent, int stars) {
		//TODO insert the data into the database
		SQLiteDatabase db = this.getWritableDatabase();
		// We use ContentValues object to store the values for
		//  the db operation
		ContentValues values = new ContentValues();
		// Store the column name as key and the description as value
		values.put(NOTE, noteContent);
		// Store the column name as key and the date as value
		values.put(RATING, stars);
		// Insert the row into the TABLE_TASK
		db.insert(TABLE_NOTE, null, values);
		// Close the database connection
		db.close();
	}

	public ArrayList<Note> getAllNotes() {
		ArrayList<Note> note = new ArrayList<Note>();
		String selectQuery = "SELECT " + COL_ID + ", "
				+ NOTE + ", "
				+ RATING
				+ " FROM " + TABLE_NOTE;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				int id = cursor.getInt(0);
				String noteContent = cursor.getString(1);
				int stars = cursor.getInt(2);
				Note obj = new Note(id, noteContent, stars);
				note.add(obj);
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return note;
	}

    public ArrayList<String> getNoteContent() {
        ArrayList<String> notes = new ArrayList<String>();
        // Select all the notes' content
        String selectQuery = "";

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);
        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row and returns true;
            // moveToNext() returns false when no more next row to move to

            do {
                notes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return notes;
    }
}
