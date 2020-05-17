/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.model;

import com.jfoenix.controls.JFXTextField;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.bll.facade.TimeTrackBLLFacade;
import timetrackingexam.bll.project.ProjectManager;
import timetrackingexam.bll.task.TaskManager;
import timetrackingexam.bll.threads.ThreadManager;
import timetrackingexam.bll.user.UserManager;

/**
 *
 * @author Rizvan
 */
public class AppModel
{
    
    private User currentUser;
    private Project currentProject;
    private Task currentTask;
    private User selectedUser;
    private TimeTrackBLLFacade ttBll;
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
        ttBll = new TimeTrackBLLFacade();
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

    public void fetch(){
        getAllUsers();
        getProjects();
        getTasks();        
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
        projects.addAll(ttBll.getAllProjects());        
        return projects;
    }
    
    public ObservableList<Task> getTasks()
    {
        tasks.clear();
        tasks.addAll(taskManager.readTask());
        return tasks;
        
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
        if(taskManager.createTask(t,p)){
            fetch();
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean updateTask(Task updateTask)
    {
        if(taskManager.updateTask(updateTask))
        {
            getTasks();
            return true;
        }
        return false;  
    }
    
    public boolean removeTask(Task selectedTask, Project currentProject)
    {
        tasks.remove(selectedTask);
        return taskManager.deleteTask(selectedTask, currentProject);
    }
    
    public void startTimer(TextField sec, TextField min, TextField hours){
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
    
    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
    
    public boolean checkIfEmailIsUsed(String email) {
        return userManager.checkIfEmailIsUsed(email);
    }
    
    public boolean addUser(User user) {
        return userManager.addUser(user);        
    }
    
    public boolean updateUser(User user) {
        return userManager.updateUser(user);        
    }
    
    public boolean deleteUser(User user) {
        return userManager.deleteUser(user);        
    }

    public ObservableList<User> GetProjectEmployees(Project p) {
        return userManager.getProjectEmployees(p);
    }

    public ObservableList<Task> getTasksInProject(Project p){
        return projectManager.getTasksInProject(p);
    }
        
    public TaskTime getTimeUsed(Task t){
       return taskManager.getTime(t);
    }
    
    public ObservableList<User> getProjectNonEmployees(Project project) {
        return userManager.getProjectNonEmployees(project);
    }
    
    public void addUsersToProject(Project project, List<User> users) {
        userManager.addUsersToProject(project, users);
    }

    public void removeUsersFromProject(Project project, List<User> users) {
        userManager.removeUsersFromProject(project, users);
    }
    
    public long getLineChartData(Task t){
        return taskManager.getLineChartData(t);
    }
    
}
