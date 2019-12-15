package com.example.todolist.dataBase.Task;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.todolist.dataBase.common.DAO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements DAO<Task> {
    public SQLiteDatabase db;

    public TaskDAO(SQLiteDatabase db) {
        this.db = db;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void save(Task task) {
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        SQLiteStatement stmt = db.compileStatement( "insert into " + TaskTable.TABLE_NAME + " ( " +
//                TaskTable.TaskColumns.ID + ", " +
                TaskTable.TaskColumns.TASK_NAME + ", " +
                TaskTable.TaskColumns.TASK_DESCRIPTION + ", " +
                TaskTable.TaskColumns.TASK_REMINDER + ", " +
                TaskTable.TaskColumns.TASK_REMINDER_DESCRIPTION +
                " ) values ( \'" +
//                task.getId() + "\', \'" +
                task.getTaskName() + "\', \'" +
                task.getTaskDescription() + "\', \'" +
                task.getTaskReminder().format(ft) + "\', \'" +
                task.getTaskReminderDescription() +
                "\' )");
        stmt.execute();
        stmt.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();

        String sql = "select * from " + TaskTable.TABLE_NAME + ";";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setTaskName(cursor.getString(1));
            task.setTaskDescription(cursor.getString(2));
            task.setTaskReminderDescription(cursor.getString(4));

            LocalDateTime date = LocalDateTime.parse(cursor.getString(3).replace(" ","T"));
            task.setTaskReminder(date);

            tasks.add(task);
        }

        return tasks;
    }

    public void updateTaskName(String newName, int taskId) {
        SQLiteStatement stmt = db.compileStatement("UPDATE " +
                TaskTable.TABLE_NAME +" SET " +
                TaskTable.TaskColumns.TASK_NAME + " = \'" +
                newName + "\' WHERE " +
                TaskTable.TaskColumns.ID + " = " +
                taskId +";"

        );
        stmt.execute();
        stmt.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateTaskReminder(LocalDateTime remindDate, int taskId) {
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        SQLiteStatement stmt = db.compileStatement("UPDATE " +
                TaskTable.TABLE_NAME +" SET " +
                TaskTable.TaskColumns.TASK_REMINDER + " = \'" +
                remindDate.format(ft) + "\' WHERE " +
                TaskTable.TaskColumns.ID + " = " +
                taskId +";"

        );
        stmt.execute();
        stmt.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Task getTaskById(int taskId) {
        List<Task> tasks = new ArrayList<>();

        String sql = "select * from " + TaskTable.TABLE_NAME +
                " where " + TaskTable.TaskColumns.ID + " = " +
                taskId
                + ";";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setTaskName(cursor.getString(1));
            task.setTaskDescription(cursor.getString(2));
            task.setTaskReminderDescription(cursor.getString(4));

            LocalDateTime date = LocalDateTime.parse(cursor.getString(3).replace(" ","T"));
            task.setTaskReminder(date);

            tasks.add(task);
        }

        return tasks.get(0);
    }
}
