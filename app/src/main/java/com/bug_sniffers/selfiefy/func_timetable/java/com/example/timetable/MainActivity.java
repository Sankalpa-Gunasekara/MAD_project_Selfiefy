package com.example.timetable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    private DbHandler dbHandler;
    private List<EventModel> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        dbHandler = new DbHandler(context);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.eventlist);
        count = findViewById(R.id.eventcount);
        events = new ArrayList<>();

        events = dbHandler.getAllEvents();

        EventAdapter adapter = new EventAdapter(context,R.layout.single_event,events);

        listView.setAdapter(adapter);

        //get event count from the table
        int countEvent = dbHandler.countEvent();
        count.setText("You have "+countEvent+" events");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, Addevent.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final EventModel event = events.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(event.getTitle());
                builder.setMessage(event.getDescription());

                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteEvent(event.getId());
                        startActivity(new Intent(context,MainActivity.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       /* Intent intent = new Intent(context,Editevent.class);
                        intent.putExtra("id",String.valueOf(event.getId()));
                        startActivity(intent);*/
                        startActivity(new Intent(context,Editevent.class));
                    }
                });
                builder.show();
            }
        });
    }
}
