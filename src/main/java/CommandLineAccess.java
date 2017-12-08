import DatabaseControl.CSVDatabaseController;
import DatabaseControl.IDatabaseController;

public class CommandLineAccess {
    private final static String DEFAULTFOLDER = "./src/main/resources/test/";
    private final static String[] DEFAULTABLENAMES ={"apotheker","klant"};
    public static void main(String[] args){
        //Parse command line arguments
        if(args == null || args.length<1){
            runDefault();
        }
    }

    public static void runDefault(){
        IDatabaseController controller = new CSVDatabaseController(DEFAULTABLENAMES,DEFAULTFOLDER);
       try {
           controller.teardown();
           controller.setup();
       }catch(Exception e){
           e.printStackTrace();
       }
    }
}
