package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    Button btnInsert, btnShowList;
    EditText noteEditText;
    DBHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        rg = (RadioGroup)findViewById(R.id.radioGroupStars);
        noteEditText = (EditText)findViewById(R.id.editTextNote);
        btnInsert = (Button)findViewById(R.id.buttonInsertNote);
        btnShowList = (Button)findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                String radiobtnText = rb.getText().toString();
                int starValue;

                if(radiobtnText.equals("1")){
                    starValue = 1;
                }
                else if (radiobtnText.equals("2")){
                    starValue = 2;

                }
                else if (radiobtnText.equals("3")){
                    starValue = 3;

                }
                else if (radiobtnText.equals("5")){
                    starValue = 4;

                }
                else{
                    starValue = 5;
                }
                db.insertNote(noteEditText.getText().toString(), starValue);
                db.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}