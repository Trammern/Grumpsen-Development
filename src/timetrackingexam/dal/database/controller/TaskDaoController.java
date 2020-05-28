/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
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

    public boolean addTask(Task t, Project p)
    {
        Connection con = conPool.checkOut();
        Boolean create = taskDAO.addTask(t, p, con);
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
    

    public ObservableList<TaskTime> getTime(Task t) {
        Connection con = conPool.checkOut();
        ObservableList<TaskTime> time = taskDAO.getTime(con, t);
        conPool.checkIn(con);
        return time;
    }

    public boolean updateTime(TaskTime tt) {
        Connection con = conPool.checkOut();
        boolean updated = taskDAO.updateTime(tt, con);
        conPool.checkIn(con);
        return updated;
    }

    public ObservableList<Task> getTasks(Project p) {
        Connection con = conPool.checkOut();
        ObservableList tasks = taskDAO.getTasks(con, p);
        conPool.checkIn(con);
        return tasks;
    }

    public boolean submitTime(TaskTime tt) {
        Connection con = conPool.checkOut();
        boolean created = taskDAO.submitTime(tt, con);
        conPool.checkIn(con);
        return created;
    }

    public ObservableList<TaskLog> getTaskLogs()
    {
        Connection con = conPool.checkOut();
        ObservableList logs = taskDAO.getLogs(con);
        conPool.checkIn(con);
        return logs;
    }

    public boolean createTimeLog(TaskLog timeLog)
    {
        Connection con = conPool.checkOut();
        boolean add = taskDAO.createTimeLog(con, timeLog);
        conPool.checkIn(con);
        return add;
    }

    public ObservableList<TaskLog> getTimeLogs()
    {
        Connection con = conPool.checkOut();
        ObservableList timelogs = taskDAO.getTimeLogs(con);
        conPool.checkIn(con);
        return timelogs;
    }

}
