/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;
import timetrackingexam.dal.facade.IProjectDal;

/**
 *
 * @author Rizvan
 */
public class ProjectDBDAO  implements Serializable
{

    private ConnectionPool pool;

    public ProjectDBDAO()
    {
        pool = ConnectionPool.getInstance();
    }

    public boolean createProject(Connection con, Project p)
    {
        String sql = "INSERT INTO Project (Name, description , Rate, clientID) VALUES (?,?,?,?)";
        try ( PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getRate());
            ps.setDouble(4, p.getClientID());

            int updatedRows = ps.executeUpdate();

            if (updatedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Project project = new Project(p.getName(), p.getDescription(), p.getClientID());
                    //missing
                }
            }
            return updatedRows > 0;
        } catch (SQLException ex)
        {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ObservableList<Project> getAllProjects(Connection con)
    {
        String sql = "SELECT * FROM Project";
        ObservableList<Project> projects = FXCollections.observableArrayList();
        try ( PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Project p = new Project(
                        rs.getString("Name"),
                        rs.getInt("ClientID"),
                        rs.getString("Description"),
                        rs.getInt("Rate"));
                p.setId(rs.getInt("ID"));
                projects.add(p);
            }
        return projects;

        } catch (SQLException ex)
        {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Boolean updateProject(Connection con, Project p)
    {
    String sql = "UPDATE project"
            + "SET project.Name = ?, project.Description = ?, project.Rate"
            + "WHERE project.ID = ?";
    try(PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
        
        ps.setString(1, p.getName());
        ps.setString(2, p.getDescription());
        ps.setInt(3, p.getRate());
        
        int updatedRows = ps.executeUpdate();
        
        return updatedRows > 0;
    }
    
    catch(SQLException sqlE){
        Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
        return false;
    }
    }

    public Boolean deleteProject(Connection con, Project p)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Jeg ved ikke om vi skal rykke denne her til task dbdao istedet. Min logik
     * er at hvis jeg skal finde en task leder jeg i projekt
     * @param p
     * @param con
     * @return 
     */
    public ObservableList<Task> getTasksInProject(Project p, Connection con) {
        ObservableList<Task> tasksInProject = FXCollections.observableArrayList();
        String sql = "SELECT * FROM task "
                + "WHERE "
                + "ProjectID = ?";
        try(PreparedStatement ps = con.prepareStatement(sql)){
            
            ps.setInt(1, p.getId());
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                tasksInProject.add(new Task(
                        rs.getInt("ID"),
                        rs.getInt("ProjectID"),
                        rs.getInt("UserID"),
                        rs.getString("Description")
                ));
            }
            return tasksInProject;
        }
        catch(SQLException sqlE){
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return null;
        }
    }
    
    public void getCSV(Connection con) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter("CSV.txt");
          StringBuilder sb= new StringBuilder();
        
        String sql = "SELECT * FROM Project";
        
        try ( PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            
            
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                 
                        sb.append(rs.getString("Name"));
                        sb.append(",");
                        sb.append( rs.getInt("ClientID"));
                        sb.append(",");
                        sb.append( rs.getString("Description"));
                        sb.append(",");
                        sb.append (rs.getInt("Rate"));
                        sb.append("\r\n");
                        
                        
             
                 
            }
          pw.write(sb.toString());
                 pw.close();
                 System.out.println("finish");

        } catch (SQLException ex)
        {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
