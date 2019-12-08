package com.example.todolist.dataBase.TaskTaskItemAsoc;

public class TaskTaskItemAsoc {
    private int taskId;
    private int taskItemId;

    public TaskTaskItemAsoc(int taskId, int taskItemId) {
        this.taskId = taskId;
        this.taskItemId = taskItemId;
    }

    public TaskTaskItemAsoc() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getTaskItemId() {
        return taskItemId;
    }

    public void setTaskItemId(int taskItemId) {
        this.taskItemId = taskItemId;
    }
}
