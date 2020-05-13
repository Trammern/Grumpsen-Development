
package timetrackingexam.dal.database.dbaccess;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;


public class DBSettings
{

    private SQLServerDataSource dataSource;

    /**
     * Sets the user credentials, ip, and port configuration
     *
     * @throws IOException
     */
    public DBSettings() throws FileNotFoundException, IOException
    {
        Properties props = new Properties();

        props.load(new FileReader("DBSettings.txt"));

        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }

    /**
     * establishes a connection to the database
     *
     * @return a Connection to the database
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}
