package Parser;

import Models.DataBaseBulkInsertData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CSVParserForDatabase implements IParser{

    public DataBaseBulkInsertData parseDataFromFile(String filePath) throws IOException {
        List<String> headers = getHeaders(filePath);
        Reader in = new FileReader(filePath);
        List<Map<String, String>> entries = new ArrayList<Map<String, String>>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            Map<String,String> entry = new HashMap<String, String>();
            for (String header:headers) {
                entry.put(header,record.get(header));
            }
            entries.add(entry);
        }
        in.close();
        return new DataBaseBulkInsertData(entries);
    }
    private List<String> getHeaders(String filePath) throws IOException {
        Reader reader = new FileReader(filePath);
        //First record contains the headers
        CSVParser parser = CSVFormat.DEFAULT.parse(reader);
        Iterator<CSVRecord> it = parser.iterator();
        CSVRecord record = it.next();
        List<String> headersAsString = new ArrayList<String>();
        for (String headerline:record) {
            headersAsString.add(headerline);
        }
        reader.close();
        return headersAsString;
    }
}
