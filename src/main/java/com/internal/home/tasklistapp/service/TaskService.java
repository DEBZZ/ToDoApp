package com.internal.home.tasklistapp.service;

import com.internal.home.tasklistapp.dao.TaskDao;
import com.internal.home.tasklistapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private TaskDao taskDao;

    @Autowired
    public TaskService(@Qualifier("postgres") TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public int createNewTask(Task task) {
        return this.taskDao.createNewTask(task);
    }

    public int addTaskExisting(Task task) {
        return this.taskDao.addTaskExisting(task);
    }
}
