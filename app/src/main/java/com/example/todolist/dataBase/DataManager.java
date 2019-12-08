package com.example.todolist.dataBase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.todolist.dataBase.Task.Task;
import com.example.todolist.dataBase.Task.TaskDAO;
import com.example.todolist.dataBase.TaskItem.TaskItem;
import com.example.todolist.dataBase.TaskItem.TaskItemDAO;

import java.time.LocalDateTime;
import java.util.List;

public class DataManager {
    public SQLiteDatabase db;
    private TaskDAO taskDAO;
    private TaskItemDAO taskItemDAO;

    public DataManager(SQLiteDatabase db) {
        this.db = db;
        taskDAO = new TaskDAO(this.db);
        taskItemDAO = new TaskItemDAO(this.db);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNewTask(String taskName,
                           String taskDescription,
                           LocalDateTime taskReminder,
                           String taskReminderDescription) {
        taskDAO.save(new Task(taskName, taskDescription, taskReminder, taskReminderDescription));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Task> getAllTasks() {
        return taskDAO.getAll();
    }

    public void updateTaskName(String newName, int taskId) {
        taskDAO.updateTaskName(newName,taskId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateTaskReminderDate(LocalDateTime newTime, int taskId) {
        taskDAO.updateTaskReminder(newTime, taskId);
    }

    public void addNewTaskItem(String itemName) {
        taskItemDAO.save(new TaskItem(itemName));
    }

    public List<TaskItem> getAllTaskItems() {
        return taskItemDAO.getAll();
    }

    public void updateItemName(String newItemName, int itemId) {
        taskItemDAO.updateTaskName(newItemName, itemId);
    }
}
