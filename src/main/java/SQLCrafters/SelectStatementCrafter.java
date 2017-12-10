package SQLCrafters;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class SelectStatementCrafter {

    Connection connection;

    public SelectStatementCrafter(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement craftTableDumpStatement(String tableName) throws SQLException {
        String sql = "Select * FROM "+tableName;
        return connection.prepareStatement(sql);

    }
}
