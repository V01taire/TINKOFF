package edu.hw3.Task7;

import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task7 {
    private static final Logger LOGGER = LogManager.getLogger(Task7.class);

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        TreeMap<String, String> treeMapWithNullKey = new TreeMap<>(new NullHandlingComparator());
        treeMapWithNullKey.put(null, "test");

        boolean containsNull = treeMapWithNullKey.containsKey(null);
        LOGGER.info("Contains null key: " + containsNull);
    }
}

