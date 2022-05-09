package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;



public class Editevent extends AppCompatActivity {

    private EditText title,time;
    private Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editevent);

        title = findViewById(R.id.editeventTextTitle);
        time = findViewById(R.id.editeventTextTime);
        edit = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        System.out.println(id);



    }
}