/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.User;
import timetrackingexam.bll.ProjectManager;
import timetrackingexam.bll.TaskManager;
import timetrackingexam.bll.ThreadManager;
import timetrackingexam.bll.UserManager;

/**
 *
 * @author Rizvan
 */
public class AppModel
{
    
    private User currentUser;
    private Project currentProject;
    private Task currentTask;
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    private final TaskManager taskManager;
    private final UserManager userManager;
    private final ProjectManager projectManager;
    private final ThreadManager tm;
    private static AppModel instance;

    private AppModel()
    {
        userManager = new UserManager();
        projectManager = new ProjectManager();
        taskManager = new TaskManager();
        tm = new ThreadManager();
    }
    
    public static AppModel getInstance() {
        if (instance == null) {
            instance = new AppModel();
        }
        return instance;
    }

    public ObservableList<User> getAllUsers()
    {
        users.clear();
        users.addAll(userManager.getAllUsers());
        return users;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;        
    }
    
    public ObservableList<Project> getProjects(){
        projects.clear();
        projects.addAll(projectManager.getAllProjects());        
        return projects;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project currentProject) {
        this.currentProject = currentProject;
    }
    
    public boolean createNewProject(Project p){
        projects.add(p);
        return projectManager.createNewProject(p);
    }
    
    public boolean updateProject(Project p) {
        if (projectManager.updateProject(p)) {
            getProjects();
            return true;
        }
        return false;
    }
    
    public void setCurrentTask(Task currentTask){
        this.currentTask = currentTask;
    }

    public Task getCurrentTask() {
        return currentTask;
    }
    
    public boolean addTask(Task t, Project p)
    {
        tasks.add(t);
        return taskManager.createTask(t,p);
    }
    
    public boolean removeTask(Task selectedTask, Project currentProject)
    {
        tasks.remove(selectedTask);
        return taskManager.deleteTask(selectedTask, currentProject);
    }
    
    public void startTimer(Label sec, Label min, Label hours){
        tm.startTimer(sec, min, hours);
    }
    
    public void pauseTimer(){
        tm.pauseTimer();
    }
    
    public void stopTimer(){
        tm.stopTimer();
    }
    
    public boolean timerIsRunning(){
        return tm.isRunning();
    }
    
    public void changePassword(User user) {
        userManager.changePassword(user);
    }
    

    
    
    
    
    
    
    
    
}
