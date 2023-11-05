package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private final String serverAddress;
    private boolean isOpen;
    private static final Logger LOGGER = LogManager.getLogger();

    public StableConnection(String serverAddress) {
        this.serverAddress = serverAddress;
        this.isOpen = true;
    }

    @Override
    public void execute(String command) {
        if (!isOpen) {
            throw new IllegalStateException("Connection is closed");
        }
        LOGGER.info("Executing command '" + command + "' on server at address: " + serverAddress);
    }

    @Override
    public void close() {
        isOpen = false;
        LOGGER.info("Connection to server at address " + serverAddress + " is closed.");
    }
}
