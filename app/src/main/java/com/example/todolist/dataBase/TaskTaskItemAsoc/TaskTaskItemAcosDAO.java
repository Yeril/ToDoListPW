package com.example.todolist.dataBase.TaskTaskItemAsoc;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.todolist.dataBase.Task.Task;
import com.example.todolist.dataBase.Task.TaskTable;
import com.example.todolist.dataBase.TaskItem.TaskItem;
import com.example.todolist.dataBase.TaskItem.TaskItemTable;
import com.example.todolist.dataBase.common.DAO;

import java.util.ArrayList;
import java.util.List;

public class TaskTaskItemAcosDAO implements DAO<TaskTaskItemAsoc> {
    public SQLiteDatabase db;

    public TaskTaskItemAcosDAO(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public void save(TaskTaskItemAsoc taskTaskItemAsoc) {
        SQLiteStatement stmt = db.compileStatement( "insert into " + TaskTaskItemAcosTable.TABLE_NAME + " ( " +
                TaskTaskItemAcosTable.TaskTaskItemAcosTableColumns.TASK_ID + ", " +
                TaskTaskItemAcosTable.TaskTaskItemAcosTableColumns.TASK_ITEM_ID + ") values ( \'" +
                taskTaskItemAsoc.getTaskId() + "\', \' " +
                taskTaskItemAsoc.getTaskItemId() + "\')");
        stmt.execute();
        stmt.close();
    }

    @Override
    public List<TaskTaskItemAsoc> getAll() {
        List<TaskTaskItemAsoc> asocs = new ArrayList<TaskTaskItemAsoc>();

        String sql = "select * from " + TaskTaskItemAcosTable.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            TaskTaskItemAsoc asoc = new TaskTaskItemAsoc();
            asoc.setTaskId(cursor.getInt(0));
            asoc.setTaskItemId(cursor.getInt(1));

            asocs.add(asoc);
        }

        return asocs;
    }

    public List<TaskItem> getAllTaskItems(Task task) {
        List<TaskItem> taskItems = new ArrayList<TaskItem>();

        String sql = "select " +
                TaskItemTable.TABLE_NAME + "." +
                TaskItemTable.TaskItemColumns.ID + ", " +
                TaskItemTable.TABLE_NAME + "." +
                TaskItemTable.TaskItemColumns.TASK_ITEM_NAME + " from " +
                TaskItemTable.TABLE_NAME + ", " +
                TaskTable.TABLE_NAME + ", " +
                TaskTaskItemAcosTable.TABLE_NAME + " where " +
                TaskTable.TABLE_NAME + "." +
                TaskTable.TaskColumns.ID + " = " +
                TaskTaskItemAcosTable.TABLE_NAME + "." +
                TaskTaskItemAcosTable.TaskTaskItemAcosTableColumns.TASK_ID + " and " +
                TaskTaskItemAcosTable.TABLE_NAME + "." +
                TaskTaskItemAcosTable.TaskTaskItemAcosTableColumns.TASK_ITEM_ID + "=" +
                TaskItemTable.TABLE_NAME + "." +
                TaskItemTable.TaskItemColumns.ID + " and " +
                TaskTable.TABLE_NAME + "." +
                TaskTable.TaskColumns.ID + "=" +
                task.getId() + ";";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            TaskItem std = new TaskItem();
            std.setId(cursor.getInt(0));
            std.setItemName(cursor.getString(1));

            taskItems.add(std);
        }

        return taskItems;
    }
}
