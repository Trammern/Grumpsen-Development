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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
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
}
