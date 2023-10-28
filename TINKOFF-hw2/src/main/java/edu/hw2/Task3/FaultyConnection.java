package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final String serverAddress;
    private static final Logger LOGGER = LogManager.getLogger();

    public FaultyConnection(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public void execute(String command) {
        LOGGER.info("Executing command on faulty connection: " + command);
    }

    @Override
    public void close() {
        LOGGER.info("Closing faulty connection to " + serverAddress);
    }
}
