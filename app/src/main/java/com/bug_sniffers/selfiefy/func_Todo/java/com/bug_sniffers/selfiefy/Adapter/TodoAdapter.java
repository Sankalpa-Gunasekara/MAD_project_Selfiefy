
package com.bug_sniffers.selfiefy.Adapter;

import android.os.Bundle;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bug_sniffers.selfiefy.AddNewTask;
import com.bug_sniffers.selfiefy.MainActivity;
import com.bug_sniffers.selfiefy.Model.TodoModel;
import com.bug_sniffers.selfiefy.R;
import com.bug_sniffers.selfiefy.Utils.DatabaseHandler;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private List<TodoModel> todoModelList;
    private MainActivity activity;
    private DatabaseHandler db;


    public TodoAdapter(DatabaseHandler db,MainActivity activity){

        this.db = db;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_layout,parent,false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull final ViewHolder holder, int position){
        db.openDatabase();
        final TodoModel item = todoModelList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                   db.updateStatus(item.getId(),1);
               }
               else{
                   db.updateStatus(item.getId(),0);
               }
            }
        });
    }

    public int getItemCount(){

        return todoModelList.size();
    }

    public Context getContext() {
        return activity;
    }

    private boolean toBoolean(int n){
        return n!=0;
    }
    public void setTasks(List<TodoModel> todoModelList){
        this.todoModelList = todoModelList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        TodoModel item = todoModelList.get(position);
        db.deleteTask(item.getId());
        todoModelList.remove(position);
        notifyItemRemoved(position);
    }

    public void editItem(int position){

        TodoModel item = todoModelList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task",item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;

        ViewHolder(View view){
            super(view);
            task = view.findViewById(R.id.checkbox_completed);
        }
    }

}
