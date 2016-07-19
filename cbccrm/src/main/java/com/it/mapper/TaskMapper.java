package com.it.mapper;


import com.it.pojo.Task;

import java.util.List;
import java.util.Map;

public interface TaskMapper {


    void addNewTask(Task task);

    void updateTask(Task task);

    void deleteTask(Map<String,Object> map);

    List<Task> queryTaskByParams(Map<String,Object> map);

    List<Task> queryOutTimeTask(Map<String, Object> map);
}
