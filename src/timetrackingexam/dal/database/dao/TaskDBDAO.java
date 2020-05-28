/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author math2
 */
public class TaskDBDAO{

    private ConnectionPool pool;
    private static TaskDBDAO INSTANCE;

    private TaskDBDAO() {
        pool = ConnectionPool.getInstance();
    }

    public static TaskDBDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskDBDAO();
            return INSTANCE;
        } else {
            return INSTANCE;
        }
    }

    public ObservableList getTasks(Connection con, Project p) {
        try{

            ObservableList<Task> tasks = FXCollections.observableArrayList();

            String sql = "SELECT * FROM task "
                    + "WHERE ProjectID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, p.getId());
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Task taskToBeAdded = new Task(
                        rs.getInt("ProjectID"),
                        rs.getInt("UserID"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
                
                taskToBeAdded.setId(rs.getInt("ID"));
                
                tasks.add(taskToBeAdded);
            }
            return tasks;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            return null;
        }
    }

    public boolean deleteTask(Connection con, Task task) {
        try{
            String sql = "DELETE FROM task "
                    + "WHERE "
                    + "ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, task.getId());

            int updatedRows = pstmt.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
    /**
     * Updated the element in the database setting a new description and name
     * @param con the connection used for the database
     * @param task the task you want to replace the old task
     * @return boolean based on weather or not the statement was executed succesfully
     */
    public boolean updateTask(Connection con, Task task) {
        try{
            String sql = "UPDATE task "
                    + "SET Name = ?, Description = ? "
                    + "WHERE ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setInt(3, task.getId());

            int updatedRows = pstmt.executeUpdate();
            System.out.println(updatedRows);
            
            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to manipulate element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }

    public Boolean addTask(Task t, Project p, Connection con) {
        try{
            String sql = "INSERT INTO task (ProjectID, Name, Description, UserID, TimeAssigned)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, t.getProjectId());
            pstmt.setString(2, t.getName());
            pstmt.setString(3, t.getDescription());
            pstmt.setInt(4, t.getUserId());
            pstmt.setInt(5, t.getTimeAssigned());
            
            int updatedRows = pstmt.executeUpdate();
                       
            return updatedRows > 0;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }

    public boolean submitTime(TaskTime tt, Connection con) {
        try{
            String sql = "INSERT INTO time (TaskID, UserID, Sec, Min, Hour, Date)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, tt.getTaskid());
            pstmt.setInt(2, tt.getUserId());
            pstmt.setInt(3, tt.getSec());
            pstmt.setInt(4, tt.getMin());
            pstmt.setInt(5, tt.getHours());
            pstmt.setString(6, tt.getDate().toString());
            
            int updatedRows = pstmt.executeUpdate();
                       
            return updatedRows > 0;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
    
    public boolean updateTime(TaskTime tt, Connection con) {
        try{
            String sql = "UPDATE time "
                    + "SET Hour = ?, Min = ?, Sec = ? "
                    + "WHERE TaskId = ? AND Date = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, tt.getHours());
            pstmt.setInt(2, tt.getMin());
            pstmt.setInt(3, tt.getSec());
            pstmt.setInt(4, tt.getTaskid());
            pstmt.setString(5, tt.getDate().toString());

            int updatedRows = pstmt.executeUpdate();
            System.out.println(updatedRows);
            
            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to manipulate element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
    
    /**
     * Slemme Mathias Lav straks det her om!!!
     * @param con
     * @param t
     * @return 
     */

    public ObservableList<TaskTime> getTime(Connection con, Task t) {
        try{
            ObservableList<TaskTime> times = FXCollections.observableArrayList();
            
            String sql = "SELECT * " +
                    "FROM Time " +
                    "WHERE TaskID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, t.getId());
            
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                TaskTime time = new TaskTime(
                        rs.getInt("TaskID"),
                        rs.getInt("UserID"),
                        rs.getInt("Sec"),
                        rs.getInt("Min"),
                        rs.getInt("Hour"),
                        LocalDate.parse(rs.getString("date"))
                );   
                time.setID(rs.getInt("ID"));
                times.add(time);
            }
            return times;
            
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return null;
        }
    }

    public ObservableList<TaskLog> getLogs(Connection con)
    {
        try
        {
            ObservableList<TaskLog> logs = FXCollections.observableArrayList();
            String sql = "SELECT * FROM TaskLog";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                TaskLog log = new TaskLog(rs.getTimestamp("date"), rs.getString("action"));
                logs.add(log);
            }
            return logs;
        } catch (SQLException ex)
        {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean createTimeLog(Connection con, TaskLog timeLog)
    {
        try
        {
            String sql = "INSERT INTO TimeLog (startDate, endDate, submittedTime) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setObject(1, timeLog.getStartDate());
            pstmt.setObject(2, timeLog.getEndDate());
            pstmt.setDouble(3, timeLog.getSubmittedTime());
            
            int updatedRows = pstmt.executeUpdate();
            
            return updatedRows > 0;

            
        } catch (SQLException ex)
        {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ObservableList getTimeLogs(Connection con)
    {
        try
        {
            ObservableList<TaskLog> timeLogs = FXCollections.observableArrayList();
            String sql = "SELECT * FROM TimeLog";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                TaskLog time = new TaskLog
                    (rs.getTimestamp("startDate").toLocalDateTime(),
                    rs.getTimestamp("endDate").toLocalDateTime(),
                    rs.getDouble("submittedTime"));
                timeLogs.add(time);
            }
            return timeLogs;
        } catch (SQLException ex)
        {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
}
