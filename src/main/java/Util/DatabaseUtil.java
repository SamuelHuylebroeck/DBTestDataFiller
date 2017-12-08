package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * @author Michael Demey
 */
public class DatabaseUtil {


    
    public static Connection openConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Unable to load database driver.");
        }
        
        Properties props = Initializer.getProps();

        Connection openConnection = DriverManager.getConnection(props.getProperty("database.url"), props.getProperty("database.username"), props.getProperty("database.password"));

        return openConnection;
    }
}
