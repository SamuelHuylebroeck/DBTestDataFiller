package Models;

import java.util.List;
import java.util.Map;

public class DataBaseBulkInsertData {

    List<Map<String,String>> entries;

    public DataBaseBulkInsertData(List<Map<String, String>> entries) {
        this.entries = entries;
    }

    public List<Map<String, String>> getEntries() {
        return entries;
    }

    public void setEntries(List<Map<String, String>> entries) {
        this.entries = entries;
    }
}
