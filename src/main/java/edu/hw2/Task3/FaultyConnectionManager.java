package edu.hw2.Task3;

public class FaultyConnectionManager implements ConnectionManager {
    private final String serverAddress;

    public FaultyConnectionManager(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(serverAddress);
    }
}
