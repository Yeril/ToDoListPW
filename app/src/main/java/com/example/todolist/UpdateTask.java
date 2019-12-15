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

public class UpdateTask extends AppCompatActivity {

    public static DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        dataManager = MainActivity.getDataManager();

        int task_id = getIntent().getIntExtra("TAKS_ID", 0);

        final Button button = findViewById(R.id.updateTask);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {

                EditText editText1 = (EditText) findViewById(R.id.updateTaskName);
                EditText editText2 = (EditText) findViewById(R.id.updateTaskDate);
                String newName = editText1.getText().toString();
                String newDate = editText2.getText().toString();

                if (!newName.equals("Task name")) {
                    dataManager.updateTaskName(
                            newName,
                            task_id
                    );
                }

                if (!newDate.equals("2019-12-24T12:00:00")) {
                    dataManager.updateTaskReminderDate(
                            LocalDateTime.parse(newDate),
                            task_id
                    );
                }


                Intent i = new Intent(UpdateTask.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
            }
        });
    }
}
