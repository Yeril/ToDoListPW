package com.example.todolist;

import android.Manifest;

import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Switch;

import com.example.todolist.dataBase.DataManager;
import com.example.todolist.dataBase.OpenHelper;
import com.example.todolist.dataBase.Task.Task;

public class MainActivity extends AppCompatActivity {

    private OpenHelper openHelper;
    private SQLiteDatabase db;

    // private DataManager dataManager;

    private Switch mySwitch;

    SharedPref sharedPref;

    public static DataManager dataManager;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);


        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Switch mySwitch = (Switch) findViewById(R.id.switch1);

        if (sharedPref.loadNightModeState() == true) {
            mySwitch.setChecked(true);
        }


        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedPref.setNightModeState(true);
                    restartApp();
                } else {
                    sharedPref.setNightModeState(false);
                    restartApp();
                }
            }
        });

        requestSmsPermission();

        openHelper = new OpenHelper(this);
        db = openHelper.getWritableDatabase();
        dataManager = new DataManager(db);

        ListView listView = findViewById(R.id.List);

        ArrayAdapter<Task> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataManager.getTasksWithItems());
        listView.setAdapter(arrayAdapter);

        final Button button = findViewById(R.id.addList);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddNewList.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, TaskScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.putExtra("TAKS_ID", task.getId());
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, UpdateTask.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.putExtra("TAKS_ID", task.getId());
                startActivity(i);
                return true;
            }
        });

    }


    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant_permission = ContextCompat.checkSelfPermission(this, permission);
        if (grant_permission != PackageManager.PERMISSION_GRANTED) {
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


    public void restartApp() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

        public static DataManager getDataManager () {
            return dataManager;
        }
}

