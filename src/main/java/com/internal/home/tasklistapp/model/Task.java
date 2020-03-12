package com.internal.home.tasklistapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;
@Getter
@Setter
public class Task {
    private UUID taskId;
    private String taskName;
    @Autowired
    @Qualifier("taskList")
    private TaskList taskList;

}
