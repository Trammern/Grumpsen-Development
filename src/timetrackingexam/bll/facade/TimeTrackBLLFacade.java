/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.facade;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.dal.facade.TimeTrackDalFacade;

/**
 *
 * @author Rizvan
 */
public class TimeTrackBLLFacade implements ITimeTrackBLL
{
    
    private TimeTrackDalFacade dalFacade;

    public TimeTrackBLLFacade() {
        this.dalFacade = new TimeTrackDalFacade();
    }

    @Override
    public boolean updateTask(Task updateTask)
    {
        return dalFacade.updateTask(updateTask);
    }

    @Override
    public List<Project> getAllProjects()
    {
        return dalFacade.getProjects();
    }

    @Override
    public boolean createNewProject(Project p)
    {
        return dalFacade.createNewProject(p);
    }

    @Override
    public boolean updateProject(Project p)
    {
        return dalFacade.updateProject(p);
    }

    @Override
    public ObservableList<User> getAllUsers()
    {
        return dalFacade.getAllUsers();
    }

    @Override
    public boolean addUser(User user)
    {
        return dalFacade.addUser(user);
    }

    @Override
    public boolean updateUser(User user)
    {
        return dalFacade.updateUser(user);
    }

    @Override
    public boolean deleteUser(User user)
    {
        return dalFacade.deleteUser(user);
    }

    @Override
    public ObservableList<User> getProjectEmployees(Project p)
    {
        return dalFacade.getProjectEmployees(p);
    }

    @Override
    public ObservableList<User> getProjectNonEmployees(Project project)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTime(TaskTime tt) {
        return dalFacade.updateTime(tt);
    }

    @Override
    public TaskTime getTime(Task t) {
        dalFacade.getTime(t);
        return dalFacade.getTime(t);
        
    }

    @Override
    public ObservableList<Task> getTasks(Project p) {
        return dalFacade.getTasks(p);
    }

    @Override
    public boolean addTask(Task t, Project p) {
        return dalFacade.addTask(t, p);
    }

    @Override
    public boolean deleteTask(Task selectedTask)
    {
        return dalFacade.deleteTask(selectedTask);
    }

    @Override
    public boolean submitTime(TaskTime tt) {
        return dalFacade.submitTime(tt);
    }

    
    

    
}
