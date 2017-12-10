import Categories.IntegrationTests;
import SQLCrafters.DeleteStatementCrafter;
import SQLCrafters.InsertStatementCrafter;
import SQLCrafters.SelectStatementCrafter;
import Util.DatabaseUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Category(IntegrationTests.class)
public class DBInsertTest {
    public static String tableName = "apotheker";
    public static Connection connection=null;

    @Before
    public void tearDown() throws SQLException {
        if(connection != null) connection.close();
        connection = DatabaseUtil.openConnection();
        DeleteStatementCrafter dsc = new DeleteStatementCrafter(connection);
        dsc.craftTableContentsDelete(tableName).execute();
    }

    @Test
    @Ignore
    public void testSimpleInsert() throws SQLException {
        Map<String,String> toInsert = new HashMap<>();
        //Fill entry
        toInsert.put("ApothekerID","1");
        toInsert.put("Naam", "Johny Test");
        toInsert.put("Straat", "Elsewhere");
        toInsert.put("Huisnummer","42");
        toInsert.put("Postcode","1001");
        toInsert.put("Gemeente","nevermore");
        //Create sql statement
        PreparedStatement ps = new InsertStatementCrafter(connection).craftInsertStatement(tableName,toInsert);
        ps.execute();
        //Query database
        ps = new SelectStatementCrafter(connection).craftTableDumpStatement(tableName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            for (Map.Entry<String,String> kvp:toInsert.entrySet()) {
                Assert.assertEquals(kvp.getValue(),rs.getString(kvp.getKey()));
            }
        }
    }

    @Test
    @Ignore
    public void testBulkInsert(){

    }
}
