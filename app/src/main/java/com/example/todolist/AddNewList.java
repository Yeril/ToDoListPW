package com.example.todolist;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.todolist.dataBase.DataManager;

import java.time.LocalDateTime;

public class AddNewList extends AppCompatActivity {

    public static DataManager dataManager;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);


        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_list);
        dataManager = MainActivity.getDataManager();

        final Button button = findViewById(R.id.addList);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {

                EditText editText1 = (EditText) findViewById(R.id.taskName);
                EditText editText2 = (EditText) findViewById(R.id.taskDescription);
                EditText editText3 = (EditText) findViewById(R.id.date);
                EditText editText4 = (EditText) findViewById(R.id.taskReminder);

                dataManager.addNewTask(
                    editText1.getText().toString(),
                    editText2.getText().toString(),
                    LocalDateTime.parse(editText3.getText().toString()),
                    editText4.getText().toString()
                );

                Intent i = new Intent(AddNewList.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
            }
        });
    }
}
