/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetrackingexam.dal.dbaccess;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rizvan
 */
public class ConnectionPool extends ObjectPool<Connection>
{
    
    private static ConnectionPool INSTANCE;
    private DBSettings dbSettings;
    
    public static synchronized ConnectionPool getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }

    private ConnectionPool()
    {
        super();
        try
        {
            dbSettings = new DBSettings();
        } catch (IOException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    protected Connection create()
    {
        try
        {
            return dbSettings.getConnection();
        } catch (SQLServerException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean validate(Connection o)
    {
        try
        {
            return !o.isClosed();
        } catch (SQLException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
    }

    @Override
    public void expire(Connection o)
    {
        try
        {
            o.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
