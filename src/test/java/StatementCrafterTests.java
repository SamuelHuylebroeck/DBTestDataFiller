import Categories.UnitTests;
import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import SQLCrafters.InsertStatementCrafter;
import Util.DatabaseUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.sql.SQLException;

@Category(UnitTests.class)
public class StatementCrafterTests {

    @Test
    public void testInsertStatementCrafter() throws IOException, SQLException {
        String filepath = "src/test/resources/statementCrafterTest.csv";

        DataBaseBulkInsertData data  = new CSVParserForDatabase().parseDataFromFile(filepath);
        String expected ="INSERT INTO test (headerOne,headerThree,headerTwo) VALUES ('one','three','two')";
        String actual = new InsertStatementCrafter(DatabaseUtil.openConnection()).craftInsertStatement("input/test",data.getEntries().get(0)).toString();
        int startPos = actual.indexOf("INSERT");
        Assert.assertEquals(expected,actual.substring(startPos));
    }
}
