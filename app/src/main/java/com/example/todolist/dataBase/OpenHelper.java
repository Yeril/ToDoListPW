package com.example.todolist.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.todolist.dataBase.Task.TaskTable;

public class OpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static String DB_NAME = "myDatabase2.db";

    public OpenHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TaskTable.onCrete(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
