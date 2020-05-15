/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.database.dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timetrackingexam.be.User;

/**
 *
 * @author fauxtistic
 */
public class UserDBDAO {
    
    public ObservableList<User> getAllUsers(Connection con) throws SQLException {
        String sql = "SELECT * FROM Employee";
        try (Statement st = con.createStatement()) {
            ObservableList<User> users = FXCollections.observableArrayList();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                User.Role role = User.Role.valueOf(rs.getString("Role"));
                int id = rs.getInt("ID");
                User user = new User(firstName, lastName, email, password, role, id); 
                users.add(user);
            }
            return users;
        }
    }
    
    public boolean createUser(Connection con, User user) throws SQLException {
        String sql = "INSERT INTO Employee (FirstName, LastName, Email, Password, Role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().toString());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        }
    }
    
    public boolean updateUser(Connection con, User user) throws SQLException {
        String sql = "UPDATE Employee SET FirstName = ?, LastName = ?, Email = ?, Password = ?, Role = ? WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().toString());
            ps.setInt(6, user.getId());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        }
    }
    
    public boolean deleteUser(Connection con, User user) throws SQLException {
        String sql = "DELETE FROM Employee WHERE ID = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            
            int updatedRows = ps.executeUpdate();
            return updatedRows > 0;
        }
    }
    
}
