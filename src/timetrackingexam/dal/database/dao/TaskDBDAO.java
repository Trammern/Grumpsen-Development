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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;
import timetrackingexam.dal.database.dbaccess.DBSettings;
import timetrackingexam.dal.facade.ITaskDal;

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
        } else {
            return INSTANCE;
        }
        return null;
    }

    public Task getSpecificTask(Connection con, Task task) {
        try{
            String sql = "SELECT * FROM task WHERE task.userId = ? AND task.projectId = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, task.getUserId());
            pstmt.setInt(2, task.getProjectId());

            ResultSet rs = pstmt.executeQuery();

            return new Task(
                    rs.getInt("ID"),
                    rs.getInt("ProjectID"),
                    rs.getInt("UserID"),
                    rs.getString("name")
            );

        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            return null;
        }
    }

    public ObservableList<Task> getAllTasks(Connection con) {
        try{

            ObservableList<Task> tasks = FXCollections.observableArrayList();

            String sql = "SELECT * FROM task";
            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("ID"),
                        rs.getInt("ProjectID"),
                        rs.getInt("UserID"),
                        rs.getString("Name")
                ));
            }
            return tasks;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            return null;
        }
    }

    public boolean createTask(Connection con, Task task) {
        try{
            String sql = "INSERT INTO task (ProjectID, Name, Description, userID, TimeAssigned)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, task.getProjectId());
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getDescription());
            pstmt.setInt(4, task.getUserId());
            pstmt.setInt(5, task.getTimeAssigned());

            int updatedRows = pstmt.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }

    public boolean deleteTask(Connection con, Task task) {
        try{
            String sql = "DELETE FROM task"
                    + "WHERE"
                    + "task.ID = ?";

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

    public boolean updateTask(Connection con, Task task) {
        try{
            String sql = "UPDATE task"
                    + "SET task.Name = ?, task.Description = ?, task.AssignedTime = ?"
                    + "WHERE task.ID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setInt(3, task.getTimeAssigned());
            pstmt.setInt(4, task.getId());

            int updatedRows = pstmt.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }

    public ObservableList<TaskTime> getTaskTime(Connection con, Task task) {
        try{

            ObservableList<TaskTime> taskTimes = FXCollections.observableArrayList();

            String sql = "SELECT * FROM Time "
                    + "INNER JOIN Task ON Task.ID = Time.TaskID";

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                taskTimes.add(new TaskTime(
                        rs.getInt("TaskID"),
                        rs.getInt("UserID"),
                        rs.getInt("Sec"),
                        rs.getInt("Min"),
                        rs.getInt("Hours"),
                        LocalDate.parse(rs.getString("Date"))
                ));
            }

            return taskTimes;
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return null;
        }
    }

}
