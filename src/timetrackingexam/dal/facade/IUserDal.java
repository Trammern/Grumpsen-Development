/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import java.util.List;
import timetrackingexam.be.User;

/**
 *
 * @author fauxtistic
 */
public interface IUserDal {
    
    public List<User> getAllUsers();
    public void changePassword(User user);
    
}
