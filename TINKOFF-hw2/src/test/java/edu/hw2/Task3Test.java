package edu.hw2;

import edu.hw2.Task3.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Task3Test {

    @Test void stableConnectionExecuteTest() {
        Connection connection = new StableConnection("example.com");
        try {
            assertDoesNotThrow(() -> connection.execute("ls"));
        } finally {
            assertDoesNotThrow(connection::close);
        }
    }

    @Test void defaultConnectionManagerTest() {
        ConnectionManager manager = new DefaultConnectionManager("example.com");
        Connection connection = manager.getConnection();
        assertNotNull(connection);
        try {
            assertDoesNotThrow(() -> connection.execute("ls"));
        } finally {
            assertDoesNotThrow(connection::close);
        }
    }

    @Test void popularCommandExecutorUpdatePackagesTest() throws ConnectionException {
        ConnectionManager mockManager = mock(ConnectionManager.class);
        Connection mockConnection = mock(Connection.class);
        when(mockManager.getConnection()).thenReturn(mockConnection);

        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3);
        executor.updatePackages();

        verify(mockManager, times(1)).getConnection();
        verify(mockConnection, times(1)).execute("apt update && apt upgrade -y");
    }

    @Test void popularCommandExecutorTryExecuteThrowsRuntimeException() throws ConnectionException {
        ConnectionManager mockManager = mock(ConnectionManager.class);
        Connection mockConnection = mock(Connection.class);
        when(mockManager.getConnection()).thenReturn(mockConnection);
        doThrow(new RuntimeException("Runtime error")).when(mockConnection).execute(anyString());

        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3);
        RuntimeException thrownException =
            assertThrows(RuntimeException.class, () -> executor.tryExecute("apt update && apt upgrade -y"));
        assertEquals("Failed to execute command", thrownException.getMessage());
    }

    @Test void popularCommandExecutorTryExecuteSuccess() throws ConnectionException {
        ConnectionManager mockManager = mock(ConnectionManager.class);
        Connection mockConnection = mock(Connection.class);
        when(mockManager.getConnection()).thenReturn(mockConnection);

        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3);
        executor.tryExecute("apt update && apt upgrade -y");

        verify(mockManager, times(1)).getConnection();
        verify(mockConnection, times(1)).execute("apt update && apt upgrade -y");
    }

    @Test void connectionExceptionConstructorTest() {
        String message = "Connection error";
        Throwable cause = new RuntimeException("Underlying cause");

        ConnectionException connectionException = new ConnectionException(message, cause);

        assertEquals(message, connectionException.getMessage());
        assertSame(cause, connectionException.getCause());
    }

    @Test void getConnectionReturnsFaultyConnectionWithCorrectAddress() {
        String serverAddress = "example.com";
        FaultyConnectionManager connectionManager = new FaultyConnectionManager(serverAddress);

        Connection connection = connectionManager.getConnection();

        assertNotNull(connection);
        assertTrue(connection instanceof FaultyConnection);
    }

}




