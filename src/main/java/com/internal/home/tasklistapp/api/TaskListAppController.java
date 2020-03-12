package com.internal.home.tasklistapp.api;

import com.internal.home.tasklistapp.model.Task;
import com.internal.home.tasklistapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequestMapping("/tasklist/")
@RestController
public class TaskListAppController {
    private TaskService taskService;

    @Autowired
    public TaskListAppController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public String createNewTask(@Valid  @NotNull @RequestBody Task task) {
        return this.taskService.createNewTask(task) == 1 ?
                "Succesfully added data in DB"
                : "Not successfull in addingdata in DB";
    }

    @PostMapping("/addtask/")
    public String addTaskExisting(@Valid  @NotNull @RequestBody Task task) {
        return this.taskService.addTaskExisting(task) == 1 ?
                "Succesfully added data in DB"
                : "Not Found";
    }
}
