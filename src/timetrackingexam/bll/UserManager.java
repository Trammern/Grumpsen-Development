/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll;

import java.util.List;
import timetrackingexam.be.User;
import timetrackingexam.dal.mockdata.MockUserDAO;

/**
 *
 * @author Rizvan
 */
public class UserManager
{
    
    private MockUserDAO mockdata;

    public UserManager()
    {
    }

    
    
    public UserManager(MockUserDAO mockdata)
    {
        this.mockdata = mockdata;
    }
    
    public List<User> getUsers()
    {
        return mockdata.getUsers();
    }
    
}
