/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import java.util.List;
import timetrackingexam.be.Project;
import timetrackingexam.dal.database.dao.ProjectDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author Rizvan
 */
public class ProjectDaoController
{
    
    private final ConnectionPool conPool;
    private final ProjectDBDAO projectDao;

    public ProjectDaoController(ConnectionPool conPool)
    {
        this.conPool = conPool;
        projectDao = new ProjectDBDAO();
    }
    
    public boolean createProject()
    {
        return false;
    }
    
    public boolean deleteProject()
    {
        return false;
    }

    public boolean editProject()
    {
        return false;
    }
    
    public List<Project> getProjects()
    {
        return null;
    }
    
    
    
}
