package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private final String serverAddress;
    private final double connectionProbability = 0.8;

    public DefaultConnectionManager(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public Connection getConnection() {
        if (new Random().nextDouble() < connectionProbability) {
            return new StableConnection(serverAddress);
        } else {
            return new FaultyConnection(serverAddress);
        }
    }
}
