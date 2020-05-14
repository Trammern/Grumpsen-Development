/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import java.util.List;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;

/**
 *
 * @author fauxtistic
 */
public interface IUserDal {
    
    public ObservableList<User> getAllUsers();    
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public ObservableList<User> getProjectEmployees(Project p); //delete
    public void addUserToProject(Project project, User user); //delete
    public void removeUserFromProject(Project project, User user); //delete
    
}
