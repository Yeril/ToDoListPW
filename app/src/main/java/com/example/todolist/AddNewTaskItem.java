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

public class AddNewTaskItem extends AppCompatActivity {

    public static DataManager dataManager;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);


        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task_item);
        dataManager = MainActivity.getDataManager();
        int task_id = getIntent().getIntExtra("TAKS_ID", 0);

        final Button button = findViewById(R.id.addNewTaskItem);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {

                EditText editText1 = (EditText) findViewById(R.id.newTaskItemName);

                dataManager.addNewTaskItem(
                        editText1.getText().toString(),
                        task_id
                );

                Intent i = new Intent(AddNewTaskItem.this, TaskScreen.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.putExtra("TAKS_ID", task_id);
                startActivity(i);
            }
        });
    }
}
