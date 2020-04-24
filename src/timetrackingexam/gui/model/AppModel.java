/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.bll.ProjectManager;
import timetrackingexam.bll.UserManager;

/**
 *
 * @author Rizvan
 */
public class AppModel
{
    
    private User currentUser;
    private Project currentProject;
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final UserManager userManager;
    private final ProjectManager projectManager;
    private static AppModel instance;

    private AppModel()
    {
        userManager = new UserManager();
        projectManager = new ProjectManager();
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
    
    
    
    
    
    
    
    
    
}
