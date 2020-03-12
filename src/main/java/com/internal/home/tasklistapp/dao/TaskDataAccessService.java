package com.internal.home.tasklistapp.dao;

import com.internal.home.tasklistapp.model.Task;
import com.internal.home.tasklistapp.model.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

@Repository("postgres")
public class TaskDataAccessService implements TaskDao{
    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskDataAccessService(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createNewTask(final UUID taskId, final Task task) {
        final String sqlTask = "INSERT INTO task(taskid,taskname) VALUES (?,?)";
        final String sqlTaskList = "INSERT INTO tasklist(taskname,task) VALUES (?,?::varchar[])";
        try {
            this.jdbcTemplate.update(sqlTask, new Object[]{taskId, task.getTaskName()}, new int[]{Types.OTHER, Types.VARCHAR});
            if (task.getTaskList() != null) {
                this.jdbcTemplate.update(sqlTaskList, new Object[]{task.getTaskName(),
                        createSqlArray(task.getTaskList().getListOfTasks())}, new int[]{Types.VARCHAR, Types.VARCHAR});
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int addTaskExisting(Task task) {
        final String checkTaskExistSql = "SELECT * FROM tasklist WHERE taskname = ?";
        List<TaskList> taskList = this.jdbcTemplate.query(checkTaskExistSql, new Object[]{task.getTaskName()},(ResultSet,i)->{
            return new TaskList((String[])ResultSet.getArray("task").getArray());
        });
        if(taskList.isEmpty()){
            createNewTask(task);
            return 1;
        }
       final String updateTaskListSql = "UPDATE tasklist ";
        return 0;
    }

    private java.sql.Array createSqlArray(String[] list){
        java.sql.Array intArray = null;
        try {
            intArray = jdbcTemplate.getDataSource().getConnection().createArrayOf("varchar", list);
        } catch (SQLException ignore) {
        }
        return intArray;
    }

}
