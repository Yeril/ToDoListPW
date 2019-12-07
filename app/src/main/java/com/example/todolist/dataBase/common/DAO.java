package com.example.todolist.dataBase.common;

import java.util.List;

public interface DAO<T> {
    void save(T t);
    List<T> getAll();
}

