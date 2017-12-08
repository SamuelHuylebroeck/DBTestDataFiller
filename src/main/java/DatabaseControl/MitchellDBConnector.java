package DatabaseControl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import SQLCrafters.DeleteStatementCrafter;
import SQLCrafters.InsertStatementCrafter;
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
            System.out.println("Inserting table: " + tableName);
            String csvPath = RESOURCEPATH + tableName+".csv";
            DataBaseBulkInsertData data = parser.parseDataFromFile(csvPath);
            try {
                this.pocessInsertData(tableName, data);
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("Finished inserting table: " + tableName);
        }


    }

    public void teardown() throws SQLException {
        //Get connection to database
        Connection connection = null;

        try{
            connection = DatabaseUtil.openConnection();
            for (String tableName:tableNames) {
                System.out.println("Cleaning table: " + tableName);
                String SQL = new DeleteStatementCrafter().craftTableContentsDelete(tableName);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.execute();
                System.out.println("Finished Cleaning table: " +tableName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null) connection.close();
        }
    }

    private void pocessInsertData(String tableName,DataBaseBulkInsertData data) throws SQLException {
        Connection connection = null;
        InsertStatementCrafter isc = new InsertStatementCrafter();
        try {

            connection = DatabaseUtil.openConnection();
            for (Map<String, String> entry : data.getEntries()) {
                //Fill statement with data
                PreparedStatement ps =  isc.craftInsertStatement(tableName,entry,connection);
                //Execute statement
                ps.execute();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }
        }
    }

}
