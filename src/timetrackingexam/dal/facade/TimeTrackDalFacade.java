/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.controller.ProjectDaoController;
import timetrackingexam.dal.database.controller.TaskDaoController;
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
    private TaskDaoController taskController;

    public TimeTrackDalFacade()
    {
        projectController = new ProjectDaoController(ConnectionPool.getInstance());
        userController = new UserDBDAOController(ConnectionPool.getInstance());
        taskController = new TaskDaoController(ConnectionPool.getInstance());
        
    }
    
    
    
    @Override
    public ObservableList<Project> getProjects()
    {
        return projectController.getProjects();
    }

    @Override
    public boolean createNewProject(Project p)
    {
        return projectController.createProject(p);
    }

    @Override
    public boolean updateProject(Project p)
    {
        return projectController.editProject(p);
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return taskController.updateTask(updateTask);
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
    
    @Override
    public ObservableList<Task> getTasks(Project p) {
        return taskController.getTasks(p);
    }

    @Override
    public TaskTime getTime(Task t) {
        return taskController.getTime(t);
    }
    
    @Override
    public boolean updateTime(TaskTime tt) {
        return taskController.updateTime(tt);
    }
    

    @Override
    public boolean addTask(Task t, Project p) {
        return taskController.addTask(t, p);
    }

    @Override
    public boolean deleteTask(Task selectedTask)
    {
        return taskController.deleteTask(selectedTask);
    }
    public void getCSV()
    {

        try
        {
            projectController.getCSV();
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(TimeTrackDalFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

      //  projectController.getCSV();

    }

    @Override
    public boolean submitTime(TaskTime tt) {
        if(!tt.getDate().toString().equals(LocalDate.now().toString())){
            return taskController.submitTime(tt);
        }
        else{
            return updateTime(tt);
        }
    }
}
