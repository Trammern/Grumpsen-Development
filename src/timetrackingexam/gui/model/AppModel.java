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
import timetrackingexam.be.Client;
import timetrackingexam.be.Project;
import timetrackingexam.be.Task;
import timetrackingexam.be.TaskLog;
import timetrackingexam.be.TaskTime;
import timetrackingexam.be.User;
import timetrackingexam.bll.facade.ITimeTrackBLL;
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
    private Client currentClient;
    private User selectedUser;
    private TaskLog tl;
    private ITimeTrackBLL ttInterface;
    private TimeTrackBLLFacade ttBll;
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();
    private final ObservableList<Client> clients = FXCollections.observableArrayList();
    private final ObservableList<TaskLog> logs = FXCollections.observableArrayList();
    private final ObservableList<TaskLog> timeLogs = FXCollections.observableArrayList();
    private final TaskManager taskManager;
    private final UserManager userManager;
    private final ProjectManager projectManager;
    private final ThreadManager tm;
    private static AppModel instance;

    private AppModel()
    {
        ttInterface = new TimeTrackBLLFacade();
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
        getTasks();    ;   
        getTimeLogs();
        getLogs();
        getProjects();           
        getAllClients();
        if (currentClient!=null) {
            getTasks();
        }         
    }
    
    public ObservableList<User> getAllUsers()
    {
        users.clear();
        users.addAll(ttInterface.getAllUsers());
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
        tasks.addAll(ttInterface.getTasks(currentProject));
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
    
    public boolean deleteProject(Project project) {
        return projectManager.deleteProject(project);
    }
    
    public void setCurrentTask(Task currentTask){
        this.currentTask = currentTask;
    }

    public Task getCurrentTask() {
        return currentTask;
    }
    
    public boolean addTask(Task t, Project p)
    {
        if(ttInterface.addTask(t, p)){
            fetch();
            fetchLogs();
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean updateTask(Task updateTask)
    {
        if(ttInterface.updateTask(updateTask))
        {
            fetch();
            fetchLogs();
            fetchTimeLogs();
            return true;
        }
        return false;  
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
        return ttInterface.checkIfEmailIsUsed(email);
    }
    
    public boolean addUser(User user) {
        return ttInterface.addUser(user);        
    }
    
    public boolean updateUser(User user) {
        return ttInterface.updateUser(user);        
    }
    
    public boolean deleteUser(User user) {
        return ttInterface.deleteUser(user);        
    }

    public ObservableList<TaskTime> getAllTime(){
        return ttInterface.getTime(currentTask);
    }
    
    public TaskTime getTotalTime(){
        return ttInterface.getTotalTime(currentTask);
    }
    
    public boolean deleteTask() {
        
        if(ttInterface.deleteTask(currentTask))
        {
            fetchLogs();
        }return true;
    }

    public boolean submitTime(TaskTime tt) {
        if(ttInterface.submitTime(tt, currentTask))
        {
            fetchTimeLogs();
            fetchLogs();
            return true;
        }
        return false;
    }
    public void getCSV()
    {
        projectManager.getCSV();
    }    
    
    public ObservableList<Client> getAllClients() {
        clients.clear();
        clients.addAll(ttInterface.getAllClients());
        return clients;
    }
    
    public boolean createClient(Client client) {
        return ttInterface.createClient(client);
    }
    
    public boolean updateClient(Client client) {
        return ttInterface.updateClient(client);
    }
    
    public boolean deleteClient(Client client) {
        return ttInterface.deleteClient(client);
    }
    
    public ObservableList<Project> getAllClientProjects(Client client) {
        return ttInterface.getAllClientProjects(client);
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }    
    
    public boolean checkIfClientNameIsUsed(String name) {
        return ttInterface.checkIfClientNameIsUsed(name);
    }
    
    public void fetchLogs()
    {
        logs.clear();
        logs.addAll(ttBll.getAllLogs());
    }
    
    public void fetchTimeLogs()
    {
        timeLogs.clear();
        timeLogs.addAll(ttBll.getAllTimeLogs());
    }
    

    public ObservableList<TaskLog> getLogs()
    {
        logs.clear();
        logs.addAll(ttBll.getAllLogs());
        return logs;
    }

    public void createTimeLog(TaskLog timeLog)
    {
        ttBll.createTimeLog(timeLog);
    }

    public ObservableList<TaskLog> getTimeLogs()
    {
        timeLogs.clear();
        timeLogs.addAll(ttBll.getAllTimeLogs());
        return timeLogs;
    }
    
    
    
}
