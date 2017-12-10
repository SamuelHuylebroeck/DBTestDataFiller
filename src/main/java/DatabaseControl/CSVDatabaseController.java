package DatabaseControl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Models.DataBaseBulkInsertData;
import Parser.CSVParserForDatabase;
import SQLCrafters.DeleteStatementCrafter;
import SQLCrafters.InsertStatementCrafter;
import Util.DatabaseUtil;

import static javafx.scene.input.KeyCode.T;

public class CSVDatabaseController implements IDatabaseController {

    //Tables
    private String[] tableNames;
    private String resourceFolderPath;
    private boolean verboseLogging = false;

    public CSVDatabaseController(String[] tableNames, String resourceFolderPath) {
        this.tableNames = tableNames;
        this.resourceFolderPath = resourceFolderPath;
        this.verboseLogging = false;
    }

    public CSVDatabaseController(String tableNamesFile, String resourceFolderPath) throws IOException {
        this(tableNamesFile, resourceFolderPath, false);
    }

    public CSVDatabaseController(String tableNamesFile, String resourceFolderPath, boolean verboseLogging) throws IOException {
        this.resourceFolderPath = resourceFolderPath;
        this.tableNames = readTableFromFile(resourceFolderPath + tableNamesFile);
        this.verboseLogging = verboseLogging;
    }

    public void setup() throws IOException {
        //Load in properties
        //Load in from CSVFiles
        CSVParserForDatabase parser = new CSVParserForDatabase();
        //Apotheker: ApothekerID,Naam,Straat,Huisnummer,Postcode,Gemeente
        //TODO: iterate over all CSV files in the folder instead of using the static String[]
        for (String tableName : tableNames) {
            System.out.println("Inserting table: " + tableName);
            String csvPath = resourceFolderPath + tableName + ".csv";
            try {
                DataBaseBulkInsertData data = parser.parseDataFromFile(csvPath);
                this.processInsertData(tableName, data);
                System.out.println("Finished inserting table: " + tableName);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    public void teardown() throws SQLException {
        //Get connection to database
        Connection connection = null;
        try {
            connection = DatabaseUtil.openConnection();
            for (int i = tableNames.length - 1; i >= 0; i -= 1) {
                System.out.println("Cleaning table: " + tableNames[i]);
                PreparedStatement ps = new DeleteStatementCrafter(connection).craftTableContentsDelete(tableNames[i]);
                try {
                    ps.execute();
                    System.out.println("Finished Cleaning table: " + tableNames[i]);
                } catch (SQLException e) {
                    System.out.println("Failed to clean");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }
    }

    private void processInsertData(String tableName, DataBaseBulkInsertData data) throws SQLException {
        Connection connection = null;

        try {
            connection = DatabaseUtil.openConnection();
            InsertStatementCrafter isc = new InsertStatementCrafter(connection);
            for (Map<String, String> entry : data.getEntries()) {
                //Fill statement with data
                PreparedStatement ps = isc.craftInsertStatement(tableName, entry);
                //Execute statement
                try {
                    ps.execute();
                } catch (SQLException e) {
                    if (verboseLogging) {
                        System.out.println("Failed to insert:");
                        System.out.println(ps.toString());
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private String[] readTableFromFile(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        BufferedReader br = new BufferedReader(reader);
        List<String> orderedListOfTableNames = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            orderedListOfTableNames.add(line);
        }
        String[] result = new String[2];
        return orderedListOfTableNames.toArray(result);

    }

}
