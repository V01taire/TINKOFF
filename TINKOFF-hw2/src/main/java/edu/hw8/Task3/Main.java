package edu.hw8.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Main {

    private static final Logger LOGGER = LogManager.getLogger();

    protected static final int MAX_THREAD_COUNT = 8;

    public static void main(String[] args) {
        long startSingle = System.currentTimeMillis();
        MonoBrute monoBrute = new MonoBrute(DB.dataBase);
        monoBrute.bruteDatabase();
        long endSingle = System.currentTimeMillis();

        LOGGER.info("single thread brute - {} millisec.", endSingle - startSingle);

        for (int i = 1; i <= MAX_THREAD_COUNT; i++) {
            long start = System.currentTimeMillis();
            MultiBrute multiBrute = new MultiBrute(DB.dataBase);
            multiBrute.bruteDatabase(i);
            long end = System.currentTimeMillis();

            LOGGER.info("multi {} thread brute - {} millisec.", i, end - start);
        }
    }
}
