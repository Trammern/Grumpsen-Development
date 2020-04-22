/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import javafx.beans.property.ObjectProperty;

/**
 *
 * @author Rizvan
 */
public class User
{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Object role;

    public User(String firstName, String lastName, String email, String password, Object role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Object getRole()
    {
        return role;
    }

    public void setRole(Object role)
    {
        this.role = role;
    }
    
    public enum Role
    {
        LeadAdmin, Admin, Default
    }
    
    
    
    
}
