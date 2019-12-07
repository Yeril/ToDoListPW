package com.example.todolist;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todolist.dataBase.DataManager;
import com.example.todolist.dataBase.OpenHelper;
import com.example.todolist.dataBase.Task.Task;

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

        openHelper = new OpenHelper(this);
        db = openHelper.getWritableDatabase();
        dataManager = new DataManager(db);

//        dataManager.addNewTask(
//                "Task1",
//                "sdfadfadsf ",
//                LocalDateTime.parse("2019-12-24T12:00:00"),
//                "jhdjhgfjhgfjhgfjhg"
//        );

        dataManager.updateTaskName("dupa8",1);
        dataManager.updateTaskReminderDate(LocalDateTime.parse("2019-12-24T12:00:01"),1);

//        ArrayList<Task> stringArrayList = new ArrayList<>();  // przykładowa statyczna lista
//        stringArrayList.add(new Task());
//        stringArrayList.add(new String("Zakupy"));
//        stringArrayList.add(new String("Praca"));
//        stringArrayList.add(new String("SMS"));

        ListView listView = findViewById(R.id.List);

        ArrayAdapter<Task> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataManager.getAllTasks());
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        ((MenuInflater) inflater).inflate(R.menu.settings_menu, menu);
        return true;
    }
}
