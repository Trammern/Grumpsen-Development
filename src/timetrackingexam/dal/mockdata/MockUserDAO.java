/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.mockdata;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.be.User.Role;
import timetrackingexam.bll.security.LoginTools;
import timetrackingexam.dal.facade.IUserDal;

/**
 *
 * @author Rizvan
 */
public class MockUserDAO implements IUserDal
{
    
    private ObservableList<User> users;
    private final ObservableList<User> projectEmployees = FXCollections.observableArrayList();

    public MockUserDAO() {
        users = FXCollections.observableArrayList();
        
        User u1 = new User("John", "Doe", "johndoe@hotmail.com", "qwerty", Role.Admin, 1);
        User u2 = new User("Billy", "Joe", "billyboy@hotmail.com", "qaz123", Role.Default, 2);
        User u3 = new User("Richard",  "Doe", "rdoe@gmail.com", "123456", Role.Default, 3);
        User u4 = new User("Admin", "Admin", "admin", "admin", Role.Admin, 4);
        User u5 = new User ("default", "default", "default", "default", Role.Default, 5);
        User u6 = new User("Daniel",  "Doe", "r", "1", Role.Default, 6);
             
        u1.setPassword(LoginTools.hashPassword(u1.getPassword()));
        u2.setPassword(LoginTools.hashPassword(u2.getPassword()));
        u3.setPassword(LoginTools.hashPassword(u3.getPassword()));
        u4.setPassword(LoginTools.hashPassword(u4.getPassword()));
        u5.setPassword(LoginTools.hashPassword(u5.getPassword()));
        u6.setPassword(LoginTools.hashPassword(u6.getPassword()));
        
        u1.assignUser(new Project(1, "Project X"));
        u2.assignUser(new Project(1, "Project X"));
        u3.assignUser(new Project(1, "Project X"));
        
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        
    }    
    
    public ObservableList<User> getAllUsers() {
        return users;
    }
    
    public boolean addUser(User user) {
        return users.add(user);
    }
    
    public boolean updateUser(User user) {
        for (User currentUser : users) {
            if (currentUser.equals(user)) {
                currentUser.setFirstName(user.getFirstName());
                currentUser.setLastName(user.getLastName());
                currentUser.setPassword(user.getPassword());
                currentUser.setRole(user.getRole());
                
            }
        }
        return true; //temporary
    }

    @Override
    public ObservableList<User> getProjectEmployees(Project p) {
        
        projectEmployees.clear();
        
        for (User user : users) {
            for (Project project : user.getProjects()) {                
                if(project.getId() == p.getId()){
                    projectEmployees.add(user);
                }
            }
        }
        
        return projectEmployees;
    }

    @Override
    public void addUserToProject(Project project, User user) {
        user.assignUser(project);
    }

    @Override
    public void removeUserFromProject(Project project, User user) {
        user.removeUser(project);
    }

    @Override
    public boolean deleteUser(User user) {
        return users.remove(user);
    }
    
    
    
}
