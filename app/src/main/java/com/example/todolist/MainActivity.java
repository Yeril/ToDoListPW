package com.example.todolist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import com.example.todolist.dataBase.DataManager;
import com.example.todolist.dataBase.OpenHelper;
import com.example.todolist.dataBase.Task.Task;
import com.example.todolist.dataBase.TaskItem.TaskItem;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private OpenHelper openHelper;
    private SQLiteDatabase db;
    private DataManager dataManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSmsPermission();

        openHelper = new OpenHelper(this);
        db = openHelper.getWritableDatabase();
        dataManager = new DataManager(db);

//        dataManager.addNewTask(
//                "Task3",
//                "Opis zadanie ",
//                LocalDateTime.parse("2019-12-09T12:00:00"),
//                "Opis przypomnienia"
//        );
//
//        dataManager.addNewTask(
//                "Task2",
//                "sdfadfadsf ",
//                LocalDateTime.parse("2019-12-24T12:00:00"),
//                "jhdjhgfjhgfjhgfjhg"
//        );
//
//        dataManager.addNewTaskItem("", 1);
//        dataManager.addNewTaskItem("Kurczak", 1);
//        dataManager.addNewTaskItem("Pies", 2);

//        dataManager.updateTaskName("nowaNazwa",1);
//        dataManager.updateTaskReminderDate(LocalDateTime.parse("2019-12-24T12:00:01"),1);

//        dataManager.addNewTaskItem("Krowa");
//        dataManager.addNewTaskItem("Kon");
//        dataManager.addNewTaskItem("cos");
//        dataManager.updateItemName("Kaczka", 3);


        ListView listView = findViewById(R.id.List);

        ArrayAdapter<Task> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataManager.getTasksWithItems());
        listView.setAdapter(arrayAdapter);
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant_permission = ContextCompat.checkSelfPermission(this, permission);
        if ( grant_permission != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        ((MenuInflater) inflater).inflate(R.menu.settings_menu, menu);
        return true;
    }
}
