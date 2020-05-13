/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.dal.database.controller.ProjectDaoController;

/**
 *
 * @author Rizvan
 */
public class ProjectDalFacade implements IProjectDal
{
    
    private ProjectDaoController projectController;

    @Override
    public ObservableList<Project> getProjects()
    {
        return (ObservableList<Project>) projectController.getProjects();
    }

    @Override
    public ObservableList<Task> getTasks()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createNewProject(Project p)
    {
        return projectController.createProject(p);
    }

    @Override
    public boolean createTask(Task t, Project p)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateProject(Project p)
    {
        return projectController.editProject(p);
    }

    @Override
    public boolean deleteTask(Task selectedTask, Project currentProject)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Task> getTimeUsed(Task t)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
