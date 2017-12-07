package SQLCrafters;

import java.util.List;
import java.util.Map;

public class InsertStatementCrafter {

    public String craftInsertStatement(String tableName, Map<String,String> entry){
        // e.g.
        //"INSERT INTO customer (email, password, company, reseller) VALUES (?,?,?,?)"
        //Preamble
        String sql = "INSERT INTO " + tableName +" (";
        int i = 0;
        for (String key:entry.keySet()) {
            sql+=key+",";
            i++;
        }
        sql=sql.substring(0,sql.length()-1);
        sql+=") VALUES (";
        //Trim the last ,

        //(?,?,...)
        for (int j = 0; j < i; j++) {
            sql+="?,";
        }
        //Trim last ,
        sql=sql.substring(0,sql.length()-1);
        sql+=")";
        return sql;
    }
}
