package com.example.selfiefydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView diaryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setDiaryAdapter();
        setOnClickListener();
    }

    private void initWidgets() {
        diaryListView = findViewById(R.id.diaryListView);
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateDiaryListArray();
    }


    private void setDiaryAdapter() {
        DiaryAdapter diaryAdapter = new DiaryAdapter(getApplicationContext(), Diary.nonDeletedDiaries());
        diaryListView.setAdapter(diaryAdapter);
    }

    private void setOnClickListener() {
        diaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Diary selectedDiary = (Diary) diaryListView.getItemAtPosition(position);
                Intent editDiaryIntent = new Intent(getApplicationContext(), DiaryDetailActivity.class);
                editDiaryIntent.putExtra(Diary.DIARY_EDIT_EXTRA, selectedDiary.getId());
                startActivity(editDiaryIntent);
            }
        });
    }


    public void newDiary(View view) {
        Intent newDiaryIntent = new Intent(this, DiaryDetailActivity.class);
        startActivity(newDiaryIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDiaryAdapter();
    }
}