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
import java.time.LocalDate;
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
// Laver Project på SQL Databasen
    public boolean createProject(Connection con, Project p)
    {
        String sql = "INSERT INTO Project (Name, Description, Rate, clientID) VALUES (?,?,?,?)";
        try ( PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getRate());
            ps.setDouble(4, p.getClientID());

            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException ex)
        {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
// Henter Alle projecter fra SQL Databasen
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
// Opdatere et Project på SQL Databasen
    public Boolean updateProject(Connection con, Project p) {
        String sql = "UPDATE Project SET Name = ?, Description = ?, Rate = ? WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setInt(3, p.getRate());
            ps.setInt(4, p.getId());

            int updatedRows = ps.executeUpdate();

            return updatedRows > 0;
        } catch (SQLException sqlE) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, sqlE);
            return false;
        }
    }
// Sletter Projecter fra SQL Databasen
    public Boolean deleteProject(Connection con, Project p)
    {
        String sql = "DELETE FROM Project WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
// Henter CSV og printer den til txt fil.
    public void getCSV(Connection con) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter("CSV.csv");
         StringBuilder sb= new StringBuilder();
        
        String sql = "SELECT * FROM Time";
                    
        
        
        try ( PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                sb.append("Date: ");
           sb.append(LocalDate.parse(rs.getString("Date")));
            sb.append("TaskID: ");
                        sb.append(rs.getInt( "TaskID" ));
                        sb.append(" UserID: ");
                        sb.append(rs.getInt("UserID"));
                        sb.append(" Hour:");
                          sb.append (rs.getInt("Hour"));
                          sb.append(" Min:");
                           sb.append (rs.getInt("Min"));
                              sb.append(" Sec:");
                        sb.append(rs.getInt("Sec"));
             
                        sb.append("\r\n");
    
            }
          pw.write(sb.toString());
                 pw.close();
                 System.out.println("TXT File Created...");

        } catch (SQLException ex)
        {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
