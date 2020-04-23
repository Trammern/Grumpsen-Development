/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.gui.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.User;
import timetrackingexam.bll.UserManager;

/**
 *
 * @author Rizvan
 */
public class AppModel
{
    
    private User currentUser;
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final UserManager userManager;
    private static AppModel instance;

    private AppModel()
    {
        userManager = new UserManager();
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
        users.addAll(userManager.getUsers());
        return users;
    }
    
    public void setCurrentUser(User user)
    {
        currentUser = user;
    }
    
    
}
