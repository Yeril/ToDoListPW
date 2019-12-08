package com.example.todolist.dataBase.TaskItem;

import android.database.sqlite.SQLiteDatabase;

public class TaskItemTable {
    public static final String TABLE_NAME = "TaskItem";

    public static class TaskItemColumns {
        public static final String ID = "Id";
        public static final String TASK_ITEM_NAME = "ItemName";
    }

    public static void onCrete(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ( " +
                TaskItemColumns.ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskItemColumns.TASK_ITEM_NAME + " TEXT);";
        db.execSQL(sql);
    }
}
