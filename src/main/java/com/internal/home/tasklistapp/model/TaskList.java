package com.internal.home.tasklistapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component("taskList")
public class TaskList {
    @JsonProperty("listOfTasks")
    private String[] listOfTasks;

    public TaskList(){}

    public TaskList(String[] listOfTasks) {
        this.listOfTasks = listOfTasks;
    }
}
