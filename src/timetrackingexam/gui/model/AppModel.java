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
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final ObservableList<Project> projects = FXCollections.observableArrayList();
    private final UserManager userManager;
    private final ProjectManager projectManager;

    public AppModel()
    {
        userManager = new UserManager();
        projectManager = new ProjectManager();
    }

    public ObservableList<User> getAllUsers()
    { 
        users.addAll(userManager.getUsers());
        return users;
    }
    
    public void setCurrentUser(User user)
    {
        currentUser = user;
    }
    
    public ObservableList<Project> getProjects(){
        projects.addAll(projectManager.getAllProjects());
        return projects;
    }
    
    
    
    
    
    
}
