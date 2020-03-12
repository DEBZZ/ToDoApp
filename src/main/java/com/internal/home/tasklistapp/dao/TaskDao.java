package com.internal.home.tasklistapp.dao;

import com.internal.home.tasklistapp.model.Task;

import java.util.UUID;

public interface TaskDao {

    int createNewTask(final UUID taskId, final Task task);

    default int createNewTask(final Task task){
        final UUID taskId = UUID.randomUUID();
        return createNewTask(taskId, task);
    }

    int addTaskExisting(final Task task);




}
