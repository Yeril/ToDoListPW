package com.example.todolist;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todolist.dataBase.DataManager;
import com.example.todolist.dataBase.Task.Task;
import com.example.todolist.dataBase.TaskItem.TaskItem;

public class TaskScreen extends AppCompatActivity {

    public static DataManager dataManager;
    private Task task;
    SharedPref sharedPref;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);


        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_screen);
        dataManager = MainActivity.getDataManager();
        int task_id = getIntent().getIntExtra("TAKS_ID", 0);

        if (task == null) {
            task = dataManager.getTaskById(task_id);
        }

        ListView listView = findViewById(R.id.List);

        ArrayAdapter<TaskItem> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataManager.getAllTaskItems(task));
        listView.setAdapter(arrayAdapter);

        final Button button = findViewById(R.id.addTaskItem);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(TaskScreen.this, AddNewTaskItem.class);
                i.putExtra("TAKS_ID", task.getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
            }
        });
    }
}
