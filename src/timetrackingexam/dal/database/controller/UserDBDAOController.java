/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import timetrackingexam.be.User;
import timetrackingexam.dal.database.dao.UserDBDAO;
import timetrackingexam.dal.database.dbaccess.ConnectionPool;

/**
 *
 * @author fauxtistic
 */
public class UserDBDAOController {
    
    private ConnectionPool pool;
    private UserDBDAO userDao;

    public UserDBDAOController(ConnectionPool pool) {
        this.pool = pool;
        userDao = new UserDBDAO();
    }
    
    public ObservableList<User> getAllUsers() {
        
        try {
            Connection con = pool.checkOut();
            ObservableList<User> users = userDao.getAllUsers(con);
            pool.checkIn(con);
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAOController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; //maybe change
        
    }
    
    public boolean addUser(User user) {
        return true;
    }
    
    public boolean updateUser(User user) {
        return true;
    }
    
    public boolean deleteUser(User user) {
        return true;
    }    
        
}
