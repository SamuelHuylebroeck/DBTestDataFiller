package DatabaseControl;

import java.io.IOException;
import java.sql.SQLException;

public interface IDatabaseController {

    void setup() throws IOException;
    void teardown() throws SQLException;
}
