package com.example.todolist.dataBase.TaskItem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.todolist.dataBase.common.DAO;

import java.util.ArrayList;
import java.util.Comparator;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getIdByItemName(String itemName) {
        List<TaskItem> taskItems = new ArrayList<>();

        String sql = "select " +
                TaskItemTable.TABLE_NAME + "." +
                TaskItemTable.TaskItemColumns.ID +
                " from " +
                TaskItemTable.TABLE_NAME +
                " where " +
                TaskItemTable.TABLE_NAME + "." +
                TaskItemTable.TaskItemColumns.TASK_ITEM_NAME + " = \'" +
                itemName + "\' "
                + ";";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            TaskItem taskItem = new TaskItem();
            taskItem.setId(cursor.getInt(0));

            taskItems.add(taskItem);
        }

        if (taskItems.size() > 1 ) {
            TaskItem taskItem = taskItems.stream().max(Comparator.comparingInt(TaskItem::getId)).get();
            return  taskItem.getId();
        }

        return taskItems.get(0).getId();
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
