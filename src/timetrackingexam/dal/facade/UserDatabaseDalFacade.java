/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.facade;

import javafx.collections.ObservableList;
import timetrackingexam.be.Project;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.controller.UserDBDAOController;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author fauxtistic
 */
public class UserDatabaseDalFacade implements IUserDal {
    
    private UserDBDAOController controller;

    public UserDatabaseDalFacade() {
        controller = new UserDBDAOController(ConnectionPool.getInstance());
    }    
    
    @Override
    public ObservableList<User> getAllUsers() {
        return controller.getAllUsers();
    }

    @Override
    public boolean addUser(User user) {
        return controller.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return controller.updateUser(user);
    }
    
    @Override
    public ObservableList<User> getProjectEmployees(Project p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUserToProject(Project project, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUserFromProject(Project project, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(User user) {
        return controller.deleteUser(user);
    }
    
    
}
