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
public class TaskDBDAO implements ITaskDal{

    private DBSettings dbConnection;
    private static TaskDBDAO INSTANCE;
    
    private TaskDBDAO()
    {
        dbConnection = new DBSettings();
    }
    
    public static TaskDBDAO getInstance (){
        if(INSTANCE == null){
            INSTANCE = new TaskDBDAO();
        }
        else{
            return INSTANCE;
        }
        return null;
    }
        
    @Override
    public Task getSpecificTask(Task task) {
        try (Connection con = dbConnection.getConnection()){
            String sql = "SELECT * FROM task WHERE task.userId = ? AND task.projectId = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, task.getUserId());
            pstmt.setInt(2, task.getProjectId());
            
            ResultSet rs = pstmt.executeQuery();
            
            return new Task(rs.getInt("projectId"), rs.getInt("UserId"), rs.getString("name"), rs.getInt("timeAssigned"));
                    
        } catch (SQLException sqlE) {
            System.out.println("Failed to grab element from database");
            return null;
        }
    }

    @Override
    public ObservableList<Task> getAllTasks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<TaskTime> getTaskTime(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
