package com.example.todolist.dataBase.Task;

import java.time.LocalDateTime;

public class Task {
    private int id;
    private String taskName;
    private String taskDescription;
    private LocalDateTime taskReminder;
    private String taskReminderDescription;

    public Task(String taskName, String taskDescription, LocalDateTime taskReminder, String taskReminderDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskReminder = taskReminder;
        this.taskReminderDescription = taskReminderDescription;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public LocalDateTime getTaskReminder() {
        return taskReminder;
    }

    public void setTaskReminder(LocalDateTime taskReminder) {
        this.taskReminder = taskReminder;
    }

    public String getTaskReminderDescription() {
        return taskReminderDescription;
    }

    public void setTaskReminderDescription(String taskReminderDescription) {
        this.taskReminderDescription = taskReminderDescription;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskReminder=" + taskReminder +
                ", taskReminderDescription='" + taskReminderDescription + '\'' +
                '}';
    }
}
