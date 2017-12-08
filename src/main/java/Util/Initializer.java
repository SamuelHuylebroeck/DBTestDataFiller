package Util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Initializer{

    private final static String PROPERTIES_FILENAME = "databaseFiller.properties";
    private static Properties props = new Properties();
    private static boolean isInitialized =false;
    private static String DEFAULTPROPERTIESPATH = "./src/main/resources/";

    public static Properties getProps() {
        if(!isInitialized) findConfigFile(DEFAULTPROPERTIESPATH);
        return props;
    }

    private static synchronized void findConfigFile(String path) {
        Logger.getLogger(Initializer.class.getName()).log(Level.INFO, "Config path: {0}", path);
        try {
            InputStream stream = new FileInputStream(path + PROPERTIES_FILENAME);
            props.load(stream);
            stream.close();
            isInitialized = true;
        } catch (IOException ex) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("No file named ");
            errorMessage.append(PROPERTIES_FILENAME);
            errorMessage.append(" in folder ");
            errorMessage.append(path);
            errorMessage.append(".\r\nThis app must have a properties file in that folder.");
            throw new ExceptionInInitializerError(errorMessage.toString());
        }
    }
}
