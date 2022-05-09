package com.example.selfiefydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class DiaryDetailActivity extends AppCompatActivity {

    private EditText dateEditText, titleEditText, descEditText;
    private Button deleteButton;
    private Diary selectedDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);
        initWidgets();
        checkForEditDiary();
    }

    private void initWidgets() {
        dateEditText = findViewById(R.id.dateEditText);
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteDiaryButton);
    }

    private void checkForEditDiary() {
        Intent previousIntent = getIntent();

        int passedDiaryID = previousIntent.getIntExtra(Diary.DIARY_EDIT_EXTRA, -1);
        selectedDiary = Diary.getDiaryForID(passedDiaryID);

        if (selectedDiary != null) {
            dateEditText.setText(selectedDiary.getDate());
            titleEditText.setText(selectedDiary.getTitle());
            descEditText.setText(selectedDiary.getDescription());
        }
        else {
            deleteButton.setVisibility(View.INVISIBLE);
        }

    }

    public void saveDiary(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String date = String.valueOf(dateEditText.getText());
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());


        if (selectedDiary == null) {

            int id = Diary.diaryArrayList.size();
            Diary newDiary = new Diary(id, date, title, desc);
            Diary.diaryArrayList.add(newDiary);
            sqLiteManager.addDiaryToDatabase(newDiary);
        }
        else {
            selectedDiary.setDate(date);
            selectedDiary.setTitle(title);
            selectedDiary.setDescription(desc);
            sqLiteManager.updateDiaryInDB(selectedDiary);
        }
        finish();
    }

    public void deleteDiary(View view) {
        selectedDiary.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateDiaryInDB(selectedDiary);
        finish();
    }
}