package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Addevent extends AppCompatActivity {

    private EditText title, time;
    private Button add;
    private DbHandler dbHandler;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        title = findViewById(R.id.editTextTitle);
        time = findViewById(R.id.editTextTime);
        add = findViewById(R.id.buttonAdd);
        context = this;

        dbHandler = new DbHandler(context);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userTitle = title.getText().toString();
                String usertime = time.getText().toString();
                long started = System.currentTimeMillis();

                EventModel eventmodel = new EventModel(userTitle, usertime, started, 0);
                dbHandler.addevent(eventmodel);

                startActivity(new Intent(context,MainActivity.class));
            }
        });
    }
}