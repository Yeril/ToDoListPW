package com.example.todolist.dataBase.Task;

import android.database.sqlite.SQLiteDatabase;

public class TaskTable {
    public static final String TABLE_NAME = "Task";

    public static class TaskColumns {
        public static final String ID = "Id";
        public static final String TASK_NAME = "taskName";
        public static final String TASK_DESCRIPTION = "taskDescription";
        public static final String TASK_REMINDER = "taskReminder";
        public static final String TASK_REMINDER_DESCRIPTION = "taskReminderDescription";

    }

    public static void onCrete(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ( " +
                TaskColumns.ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskColumns.TASK_NAME + " TEXT, " +
                TaskColumns.TASK_DESCRIPTION + " TEXT, " +
                TaskColumns.TASK_REMINDER + " DATETIME, " +
                TaskColumns.TASK_REMINDER_DESCRIPTION + " TEXT);";

        db.execSQL(sql);
    }
}
