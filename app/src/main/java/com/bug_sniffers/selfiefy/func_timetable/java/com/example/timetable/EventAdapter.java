package com.example.timetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
//import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<EventModel> {

    private Context context;
    private int resource;
    List<EventModel> events;

    EventAdapter(Context context , int resource, List<EventModel> events){
        super(context,resource,events);
        this.context = context;
        this.resource = resource;
        this.events = events;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource,parent,false);

        TextView title = row.findViewById(R.id.title);
        TextView description = row.findViewById(R.id.description);
       // ImageView imageView = row.findViewById(R.id.onGoing);


        // todos [obj1,obj2,obj3]
        EventModel event = events.get(position);
        title.setText(event.getTitle());
        description.setText(event.getDescription());
        //imageView.setVisibility(row.INVISIBLE);

        /*if(toDo.getFinished() > 0){
            imageView.setVisibility(View.VISIBLE);
        }*/
        return row;
    }
}
