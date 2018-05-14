package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {


	ArrayAdapter aa;
	ArrayList<Note>notes;
	DBHelper db;
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		lv = (ListView) findViewById(R.id.lv);

		db = new DBHelper(this);
		db.getWritableDatabase();
		notes = db.getAllNotes();
		db.close();
		aa = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, notes);
		lv.setAdapter(aa);

	}


}
