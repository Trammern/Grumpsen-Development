/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.controller.ProjectDaoController;
import timetrackingexam.dal.database.controller.UserDBDAOController;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author Rizvan
 */
public class TimeTrackDalFacade implements ITimeTrackDalFacade
{

    private ProjectDaoController projectController;
    private UserDBDAOController userController;

    public TimeTrackDalFacade()
    {
        projectController = new ProjectDaoController(ConnectionPool.getInstance());
        userController = new UserDBDAOController(ConnectionPool.getInstance());
    }
    
    
    
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

    @Override
    public Task getSpecificTask(Task task)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Task> getAllTasks()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean createTask(Task task)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteTask(Task task)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<TaskTime> getTaskTime(Task task)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<User> getAllUsers()
    {
        return userController.getAllUsers();
    }

    @Override
    public boolean addUser(User user)
    {
        return userController.addUser(user);
    }

    @Override
    public boolean updateUser(User user)
    {
        return userController.updateUser(user);
    }

    @Override
    public ObservableList<User> getProjectEmployees(Project p)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUserToProject(Project project, User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUserFromProject(Project project, User user)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(User user) {
        return userController.deleteUser(user);
    }
    
}