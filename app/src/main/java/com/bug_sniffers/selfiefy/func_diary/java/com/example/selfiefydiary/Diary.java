package com.example.selfiefydiary;

import java.util.ArrayList;
import java.util.Date;

public class Diary {

    public static ArrayList<Diary> diaryArrayList = new ArrayList<>();
    public static String DIARY_EDIT_EXTRA = "diaryEdit";

    private int id;
    private String date;
    private String title;
    private String description;
    private Date deleted;

    public Diary(int id, String date, String title, String description, Date deleted) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        this.deleted = deleted;
    }

    public Diary(int id, String date, String title, String description) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.description = description;
        deleted = null;
    }

    public static Diary getDiaryForID(int passedDiaryID) {
        for (Diary diary : diaryArrayList) {
            if (diary.getId() == passedDiaryID)
                return diary;
        }
        return null;
    }

    public static ArrayList<Diary> nonDeletedDiaries() {
        ArrayList<Diary> nonDeleted = new ArrayList<>();
        for (Diary diary : diaryArrayList) {
            if (diary.getDeleted() == null)
                nonDeleted.add(diary);
        }

        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
