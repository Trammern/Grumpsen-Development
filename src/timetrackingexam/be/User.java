/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.be;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.ObjectProperty;

/**
 *
 * @author Rizvan
 */
public class User
{
    private String firstName;
    private String lastName;
    private final String email;
    private String password;
    private Role role;
    private List<Project> projects;
    private List<Task> tasks;

    public User(String firstName, String lastName, String email, String password, Role role)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        projects = new ArrayList<>();
        tasks = new ArrayList<>();
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public boolean assignUser(Project p){
        p.getUsers().add(this);
        return projects.add(p);
    }
    
    public boolean removeUser(Project p) {
        p.getUsers().remove(this);
        return projects.remove(p);
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName;
    }
    
    
        
    /**
     * LeadAdmin can make other users into admins
     * Admin can create projects and see overview of users (who works on what, hours etc.)
     * Default is just a normal user who can time track and see his own work etc.
     */
    public enum Role
    {
        Admin, Default
    }
    
    
    
    
}
