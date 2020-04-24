/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.mockdata;

import java.util.ArrayList;
import java.util.List;
import timetrackingexam.be.User;
import timetrackingexam.be.User.Role;

/**
 *
 * @author Rizvan
 */
public class MockUserDAO
{
    
    public List<User> getUsers()
    {
        List<User> users = new ArrayList<User>();
        
        User u1 = new User("John", "Doe", "johndoe@hotmail.com", "qwerty", Role.Admin);
        User u2 = new User("Billy", "Joe", "billyboy@hotmail.com", "qaz123", Role.Default);
        User u3 = new User("Richard",  "Doe", "rdoe@gmail.com", "123456", Role.Default);
        User u4 = new User("Admin", "Admin", "admin", "admin", Role.Admin);
        User u5 = new User ("default", "default", "default", "default", Role.Default);
        User u6 = new User("Richard",  "Doe", "r", "1", Role.Default);
        
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        
        return users;
    }
    
}
