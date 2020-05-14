/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import java.sql.SQLException;
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
    
    public boolean createProject(Project p)
    {
        Connection con = conPool.checkOut();
        Boolean project = projectDao.createProject(con, p);
        conPool.checkIn(con);
        return project;
    }
    
    public boolean deleteProject()
    {
        Connection con = conPool.checkOut();
        Boolean delete = true;//delete project from dao
        conPool.checkIn(con);
        return delete;
    }

    public boolean editProject(Project p)
    {
        Connection con = conPool.checkOut();
        Boolean edit = projectDao.updateProject(con, p);
        conPool.checkIn(con);
        return edit;
    }
    
    public List<Project> getProjects()
    {
        Connection con = conPool.checkOut();
        List<Project> allProjects = projectDao.getAllProjects(con);
        conPool.checkIn(con);
        return allProjects;
    }


    
    
    
}
