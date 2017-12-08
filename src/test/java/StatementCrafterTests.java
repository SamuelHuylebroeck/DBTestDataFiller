import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import SQLCrafters.InsertStatementCrafter;
import Util.DatabaseUtil;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

public class StatementCrafterTests {

    @Test
    public void testInsertStatementCrafter() throws IOException, SQLException {
        String filepath = "src/test/resources/statementCrafterTest.csv";
        DataBaseBulkInsertData data  = new CSVParserForDatabase().parseDataFromFile(filepath);
        String expected ="";
        String actual = new InsertStatementCrafter().craftInsertStatement("test",data.getEntries().get(0), DatabaseUtil.openConnection()).toString();
        System.out.println(actual);
    }
}
