/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.user;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.dal.facade.IUserDal;
import timetrackingexam.dal.facade.TimeTrackDalFacade;
import timetrackingexam.dal.mockdata.MockUserDAO;

/**
 *
 * @author Rizvan
 */
public class UserManager implements IUserManager
{
    
    private IUserDal userDal;

    public UserManager()
    {
        userDal = new TimeTrackDalFacade();
    }

    @Override
    public ObservableList<User> getAllUsers()
    {
        return userDal.getAllUsers();
    }
    
    @Override
    public boolean checkIfEmailIsUsed(String email) {
        
        boolean used = false;
        
        for (User user : getAllUsers()) {
            if (user.getEmail().equals(email)) {
                used = true;
            }
        }
        
        return used;
    }
    
    @Override
    public boolean addUser(User user) {
        return userDal.addUser(user);
    }
    
    @Override
    public boolean updateUser(User user) {
        return userDal.updateUser(user);
    }
    
    @Override
    public boolean deleteUser(User user) {
        return userDal.deleteUser(user);
    }

}
