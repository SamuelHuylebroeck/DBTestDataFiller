package Parser;

import Models.DataBaseBulkInsertData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface IParser {
    DataBaseBulkInsertData parseDataFromFile(String filePath) throws IOException;
}
