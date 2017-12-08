import Categories.IntegrationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.sql.Connection;

@Category(IntegrationTests.class)
public class DBRemoveTest {
    public static String tableName = "apotheker";
    public static Connection connection=null;

    @Before
    public void setup(){
        //Tear down existing

        //Setup with new dummy data
    }

    @Test
    public void testTableContentsRemove(){

    }
}
