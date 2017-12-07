package DatabaseControl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import SQLCrafters.DeleteStatementCrafter;
import Util.DatabaseUtil;

public class MitchellDBConnector implements IDatabaseController{

    //Tables
    private static String[] tableNames = {"apotheker"};

    private final static String RESOURCEPATH = "";

    public void setup() throws IOException {
        //Load in from CSVFiles
        CSVParserForDatabase parser = new CSVParserForDatabase();
        //Apotheker: ApothekerID,Naam,Straat,Huisnummer,Postcode,Gemeente
        //TODO: iterate over all CSV files in the folder instead of using the static String[]
        for (String tableName: tableNames) {
            String csvPath = RESOURCEPATH + tableName+".csv";
            DataBaseBulkInsertData data = parser.parseDataFromFile(csvPath);
            this.pocessInsertData(tableName,data);
        }


    }

    public void teardown() throws SQLException {
        //Get connection to database
        Connection connection = null;

        try{
            connection = DatabaseUtil.openConnection();
            for (String tableName:tableNames) {
                String SQL = new DeleteStatementCrafter().craftTableContentsDelete(tableName);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.execute();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null) connection.close();
        }
    }

    private void pocessInsertData(String tableName,DataBaseBulkInsertData data){

    }

}
