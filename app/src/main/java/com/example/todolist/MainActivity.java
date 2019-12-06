package com.example.todolist;

import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> stringArrayList = new ArrayList<>();  // przykładowa statyczna lista
        stringArrayList.add(new String("Egzaminy"));
        stringArrayList.add(new String("Zakupy"));
        stringArrayList.add(new String("Praca"));
        stringArrayList.add(new String("SMS"));

        ListView listView = findViewById(R.id.List);

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        ((MenuInflater) inflater).inflate(R.menu.settings_menu, menu);
        return true;
    }
}
