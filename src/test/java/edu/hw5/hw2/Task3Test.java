package edu.hw2;

import edu.hw2.Task3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void stableConnectionExecuteTest() {
        Connection connection = new StableConnection("example.com");
        try {
            assertDoesNotThrow(() -> connection.execute("ls"));
        } finally {
            assertDoesNotThrow(connection::close);
        }
    }

    @Test
    void defaultConnectionManagerTest() {
        ConnectionManager manager = new DefaultConnectionManager("example.com");
        Connection connection = manager.getConnection();
        assertNotNull(connection);
        try {
            assertDoesNotThrow(() -> connection.execute("ls"));
        } finally {
            assertDoesNotThrow(connection::close);
        }
    }

}




