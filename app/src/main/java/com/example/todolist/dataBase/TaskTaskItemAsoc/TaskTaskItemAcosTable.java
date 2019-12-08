package com.example.todolist.dataBase.TaskTaskItemAsoc;

import android.database.sqlite.SQLiteDatabase;
import com.example.todolist.dataBase.Task.TaskTable;
import com.example.todolist.dataBase.TaskItem.TaskItemTable;

public class TaskTaskItemAcosTable {
    public static final String TABLE_NAME = "TaskTaskItemAcosTable";

    public static class TaskTaskItemAcosTableColumns {
        public static final String TASK_ID = "taskId";
        public static final String TASK_ITEM_ID = "taskItemId";
    }

    public static void onCrete(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ( " +
                TaskTaskItemAcosTableColumns.TASK_ID + "  INTEGER, " +
                TaskTaskItemAcosTableColumns.TASK_ITEM_ID + " INTEGER, FOREIGN KEY(" +
                TaskTaskItemAcosTableColumns.TASK_ID + " ) REFERENCES " +
                TaskTable.TABLE_NAME + "(" +
                TaskTable.TaskColumns.ID + "), FOREIGN KEY(" +
                TaskTaskItemAcosTableColumns.TASK_ITEM_ID + " ) REFERENCES " +
                TaskItemTable.TABLE_NAME + "(" +
                TaskItemTable.TaskItemColumns.ID +")" +
                ");";
        db.execSQL(sql);
    }
}
