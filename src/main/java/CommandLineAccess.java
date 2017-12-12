import DatabaseControl.CSVDatabaseController;
import DatabaseControl.IDatabaseController;
import Util.Initializer;
import Util.TestDataGenerator;

import java.io.IOException;
import java.sql.SQLException;

public class CommandLineAccess {
    private static String DEFAULTTABLENAMESFILE = "TableNames.tn";
    public static void main(String[] args) throws Exception {
        System.out.println("Starting up database filler and emptier (better name pending from command line");
        final String dir = System.getProperty("user.dir");
        System.out.println("current directory is " + dir);
        //Parse command line arguments
        if (args == null || args.length < 1) {
            runDefault();
        } else {
            for (int i = 0; i < args.length; i++) {
                //A solo argument starts with -- (e.g. --g for generate)
                if (args[i].startsWith("--")) {
                    executeSoloArgumentCall(args[i]);
                }
                //A pair of arguments starts with an identifier -x followed by its argument. It returns the number of arguments consumed? --> To decide
            }
        }
    }


    public static void runDefault() {
        try {
            System.out.println("Running in default mode");
            String defaultFolder = Initializer.getProps().getProperty("data.inputfolder");
            System.out.println("Looking for input in: " + defaultFolder);
            IDatabaseController controller = new CSVDatabaseController(DEFAULTTABLENAMESFILE, defaultFolder, true);
            controller.teardown();
            controller.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void executeSoloArgumentCall(String argument) throws IOException, SQLException {
        String folder;
        int nrOfEntries;
        switch (argument) {
            case "--g":
                System.out.println("Running in data generation mode with header");
                folder = Initializer.getProps().getProperty("data.generation.output");
                System.out.println("Outputting to: " + folder);
                nrOfEntries = Integer.parseInt(Initializer.getProps().getProperty("data.generation.nrOfEntries"));
                System.out.println("Nr of entries per file: " + nrOfEntries);
                TestDataGenerator.generateTestData(folder, nrOfEntries,true);
            case "--gnh":
                System.out.println("Running in data generation mode without header");
                folder = Initializer.getProps().getProperty("data.generation.output");
                System.out.println("Outputting to: " + folder);
                nrOfEntries = Integer.parseInt(Initializer.getProps().getProperty("data.generation.nrOfEntries"));
                System.out.println("Nr of entries per file: " + nrOfEntries);
                TestDataGenerator.generateTestData(folder, nrOfEntries,false);
            case "--clean":
                System.out.println("Cleaning database");
                String defaultFolder = Initializer.getProps().getProperty("data.inputfolder");
                System.out.println("Looking for input in: " + defaultFolder);
                IDatabaseController controller = new CSVDatabaseController(DEFAULTTABLENAMESFILE, defaultFolder, true);
                controller.teardown();
            default:
                break;
        }
    }
}
