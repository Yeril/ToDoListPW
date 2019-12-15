package com.example.todolist.dataBase.TaskItem;

public class TaskItem {
    private int id;
    private String itemName;

    public TaskItem(String taskName) {
        this.itemName = taskName;
    }

    public TaskItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return itemName + ' ';
    }
}
