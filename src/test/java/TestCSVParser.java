import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import org.apache.commons.csv.CSVParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCSVParser {

    @Test
    public void testCsvParsing() throws IOException {
        String filepath = "src/test/resources/test.csv";
        CSVParserForDatabase parser = new CSVParserForDatabase();
        DataBaseBulkInsertData bulkData = parser.parseDataFromFile(filepath);
        List<Map<String,String>> actual = bulkData.getEntries();
        for (Map<String, String> entry: bulkData.getEntries()) {
            System.out.println("New entry");
            for (Map.Entry<String,String> kvp:entry.entrySet()) {
                System.out.println(kvp.getKey() +": "+kvp.getValue());
            }
        }
        List<Map<String,String>> expected = new ArrayList<>();
        Assert.assertEquals(expected,actual);
    }
}
