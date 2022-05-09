package com.example.selfiefydiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DiaryAdapter extends ArrayAdapter<Diary> {

    public DiaryAdapter(Context context, List<Diary> diaries) {

        super(context, 0, diaries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Diary diary = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.diary_cell, parent, false);

        TextView date = convertView.findViewById(R.id.cellDate);
        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        date.setText(diary.getDate());
        title.setText(diary.getTitle());
        desc.setText(diary.getDescription());

        return convertView;
    }
}
