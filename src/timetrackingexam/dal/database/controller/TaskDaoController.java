/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import javafx.collections.ObservableList;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.dal.database.dao.TaskDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author Rizvan
 */
public class TaskDaoController
{
    private final ConnectionPool conPool;
    private final TaskDBDAO taskDAO;

    public TaskDaoController(ConnectionPool conPool)
    {
        this.conPool = conPool;
        taskDAO = TaskDBDAO.getInstance();
    }
    
    public Task getSpecificTask(Task t)
    {
        Connection con = conPool.checkOut();
        Task selectetTask = taskDAO.getSpecificTask(con, t);
        conPool.checkIn(con);
        return selectetTask;
    }
    
    public ObservableList<Task> getAllTasks()
    {
        Connection con = conPool.checkOut();
        ObservableList<Task> tasks = taskDAO.getAllTasks(con);
        conPool.checkIn(con);
        return tasks;
    }

    public boolean CreateTask(Task task)
    {
        Connection con = conPool.checkOut();
        Boolean create = taskDAO.createTask(con, task);
        conPool.checkIn(con);
        return create;
    }
    
    public boolean deleteTask(Task task)
    {
        Connection con = conPool.checkOut();
        boolean deleted = taskDAO.deleteTask(con, task);
        conPool.checkIn(con);
        return deleted;
    }
    
    public boolean updateTask(Task task)
    {
        Connection con = conPool.checkOut();
        boolean updated = taskDAO.updateTask(con, task);
        conPool.checkIn(con);
        return updated;
    }
    

    public TaskTime getTime(Task t) {
        Connection con = conPool.checkOut();
        TaskTime time = taskDAO.getTime(con, t);
        conPool.checkIn(con);
        return time;
    }
}
