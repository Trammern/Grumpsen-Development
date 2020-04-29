/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll;

import java.util.List;
import timetrackingexam.be.User;
import timetrackingexam.dal.facade.IUserDal;
import timetrackingexam.dal.mockdata.MockUserDAO;

/**
 *
 * @author Rizvan
 */
public class UserManager
{
    
    private IUserDal userDal;

    public UserManager()
    {
        userDal = new MockUserDAO();
    }

    public List<User> getAllUsers()
    {
        return userDal.getAllUsers();
    }
    
    public void changePassword(User user) {
        userDal.changePassword(user);
    }
    
}
