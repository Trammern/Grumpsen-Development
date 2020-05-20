/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.bll.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            if (user.getEmail().equals(email) && user.getPassword().equals(hashPassword(password)))
            {
                verifiedUser = user;                
            }
        }      
        return verifiedUser;
    }
    
    public static String getVerifiedNewPassword(User user, String oldPassword, String newPassword) {
        String password = null;
        if (user.getPassword().equals(hashPassword(oldPassword))) {
            password = hashPassword(newPassword);
        }
        
        return password;
    }
    
    public static boolean validateEmail(String email) {
        return email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
    }
    
    /**
     * Password must contain one number
     * @param password
     * @return 
     */
    public static boolean validatePassword(String password)
    {
        return password.matches("^(?=.*[0-9])$");
    }
    
    public static String hashPassword(String password) { 
        String base = password;
        try {            
            MessageDigest digest = null;
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();      
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginTools.class.getName()).log(Level.SEVERE, null, ex);
            AlertBox.showErrorAlert("Error in user login system");
        }
        return null;
    }
}
