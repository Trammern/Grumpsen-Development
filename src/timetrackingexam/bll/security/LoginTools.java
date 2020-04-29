/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.security;

import java.util.List;
import timetrackingexam.be.User;
import timetrackingexam.gui.util.AlertBox;

/**
 *
 * @author fauxtistic
 */
public class LoginTools {    
    
    public static User getVerifiedUser(String email, String password, List<User> users)
    {       
        User verifiedUser = null;
        
        for (User user : users)
        {
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
            {
                verifiedUser = user;                
            }
        }        
        return verifiedUser;
    }
    
    public static String getVerifiedNewPassword(User user, String oldPassword, String newPassword) {
        String password = null;
        if (user.getPassword().equals(oldPassword)) {
            password = newPassword;
        }
        
        return password;
    }
}
