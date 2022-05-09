package com.example.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DB_NAME = "event";
    private static final String TABLE_NAME = "event";

    // Column names
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +TITLE + " TEXT,"
                +DESCRIPTION + " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+" TEXT" +
                ");";

        /*
            CREATE TABLE event (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT,
            started TEXT,finished TEXT); */

        sqLiteDatabase.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        // Drop older table if existed
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(sqLiteDatabase);
    }

    public void addevent(EventModel eventmodel) {
        SQLiteDatabase sqLitedb = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, eventmodel.getTitle());
        contentValues.put(DESCRIPTION, eventmodel.getDescription());
        contentValues.put(STARTED, eventmodel.getStarted());
        contentValues.put(FINISHED, eventmodel.getFinished());

        //save to table
        sqLitedb.insert(TABLE_NAME, null, contentValues);
        // close database
        sqLitedb.close();
    }

        //count event records
        public int countEvent(){
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT * FROM "+ TABLE_NAME;

            Cursor cursor = db.rawQuery(query,null);
            return cursor.getCount();
        }

    // Get all events into a list
    public List<EventModel> getAllEvents(){

        List<EventModel> events = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new event object
                EventModel event = new EventModel();

                event.setId(cursor.getInt(0));
                event.setTitle(cursor.getString(1));
                event.setDescription(cursor.getString(2));
                event.setStarted(cursor.getLong(3));
                event.setFinished(cursor.getLong(4));

                //toDos [obj,objs,asas,asa]
                events.add(event);
            }while (cursor.moveToNext());
        }
        return events;
    }

    //Delete item
    public void deleteEvent(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

}
