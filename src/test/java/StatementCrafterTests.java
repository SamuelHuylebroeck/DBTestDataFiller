import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import SQLCrafters.InsertStatementCrafter;
import org.junit.Test;

import java.io.IOException;

public class StatementCrafterTests {

    @Test
    public void testInsertStatementCrafter() throws IOException {
        String filepath = "src/test/resources/statementCrafterTest.csv";
        DataBaseBulkInsertData data  = new CSVParserForDatabase().parseDataFromFile(filepath);
        String expected ="";
        String actual = new InsertStatementCrafter().craftInsertStatement("test",data.getEntries().get(0));
        System.out.println(actual);
    }
}
