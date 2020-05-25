/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.user;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;

/**
 *
 * @author Rizvan
 */
public interface IUserManager
{
    public ObservableList<User> getAllUsers();
    
    public boolean addUser(User user);
    
    public boolean updateUser(User user);
    
    public boolean deleteUser(User user);    
   
    public boolean checkIfEmailIsUsed(String email);
}
