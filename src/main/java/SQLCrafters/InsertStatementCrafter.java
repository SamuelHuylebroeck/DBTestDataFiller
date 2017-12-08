package SQLCrafters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InsertStatementCrafter {

    public PreparedStatement craftInsertStatement(String tableName, Map<String,String> entry, Connection connection) throws SQLException {
        // e.g.
        //"INSERT INTO customer (email, password, company, reseller) VALUES (?,?,?,?)"
        //Preamble
        String sql = "INSERT INTO " + tableName +" (";
        String values = ") VALUES (";
        List<String> valuesToInsert = new ArrayList<>();
        int i = 0;
        for (Map.Entry<String,String> kvp:entry.entrySet()) {
            sql+=kvp.getKey()+",";
            values += "?,";
            valuesToInsert.add(kvp.getValue());
        }
        sql=sql.substring(0,sql.length()-1);
        values.substring(0,values.length()-1);
        sql+=values+")";

        //Turn into prepared statement
        PreparedStatement ps = connection.prepareStatement(sql);
        for (int j = 1; j <= valuesToInsert.size(); j++) {
            ps.setString(j,valuesToInsert.get(--j));
        }

        return ps;
    }
}
