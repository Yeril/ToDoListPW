package com.example.todolist.dataBase.TaskItem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.todolist.dataBase.common.DAO;

import java.util.ArrayList;
import java.util.List;

public class TaskItemDAO implements DAO<TaskItem> {
    public SQLiteDatabase db;

    public TaskItemDAO(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void save(TaskItem taskItem) {
        SQLiteStatement stmt = db.compileStatement( "insert into " + TaskItemTable.TABLE_NAME + " ( " +
                TaskItemTable.TaskItemColumns.TASK_ITEM_NAME + " " +
                " ) values ( \'" +
                taskItem.getItemName() + "\'" +
                ")");
        stmt.execute();
        stmt.close();
    }

    @Override
    public List<TaskItem> getAll() {
        List<TaskItem> taskItems = new ArrayList<>();

        String sql = "select * from " + TaskItemTable.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            TaskItem taskItem = new TaskItem();
            taskItem.setId(cursor.getInt(0));
            taskItem.setItemName(cursor.getString(1));

            taskItems.add(taskItem);
        }

        return taskItems;
    }

    public void updateTaskName(String newItemName, int itemId) {
        SQLiteStatement stmt = db.compileStatement("UPDATE " +
                TaskItemTable.TABLE_NAME +" SET " +
                TaskItemTable.TaskItemColumns.TASK_ITEM_NAME + " = \'" +
                newItemName + "\' WHERE " +
                TaskItemTable.TaskItemColumns.ID + " = " +
                itemId +";"

        );
        stmt.execute();
        stmt.close();
    }
}
