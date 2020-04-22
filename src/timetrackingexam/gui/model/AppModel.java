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
    
    private User user;
    private final ObservableList<User> users = FXCollections.observableArrayList();
    private final UserManager userManager;

    public AppModel()
    {
        userManager = new UserManager();
    }

    
    public ObservableList<User> getAllUsers()
    { 
        users.addAll(userManager.getUsers());
        return users;
    }
    
    
    
}
