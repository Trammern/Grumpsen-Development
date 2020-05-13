/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import timetrackingexam.be.Client;
import com.microsoft.sqlserver.jdbc.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jonas
 */
public class ClientDBDAO {
    
    private DBSettings dbs;

    public ClientDBDAO() throws IOException {
      
            dbs = new DBSettings();

        }
 public void setClient() throws SQLServerException {
            
        try (Connection con = dbs.getConnection()) {
            String sql = "SELECT * FROM Client;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Client> client = new ArrayList<>();
            
            while (rs.next()) {
                int clientId = rs.getInt("ClientId");
                int taskId = rs.getInt("TaskId");
                String name = rs.getString("Task");

                Client c = new Client(name, clientId, taskId);               
                
                client.add(c);    
                                        
            }
}       catch (SQLException ex) {
            Logger.getLogger(ClientDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 }
}