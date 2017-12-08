package SQLCrafters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteStatementCrafter {

    private Connection connection;

    public DeleteStatementCrafter(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement craftTableContentsDelete(String tableToDelete) throws SQLException {
        String sql =  "DELETE FROM " + tableToDelete+";";
        return connection.prepareStatement(sql);
    }
}
