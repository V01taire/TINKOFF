package edu.hw3;

import edu.hw3.Task7.NullHandlingComparator;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Task7Test {

    @Test
    void testTreeMapContainsNullKey() {
        TreeMap<String, String> treeMapWithNullKey = new TreeMap<>(new NullHandlingComparator());
        treeMapWithNullKey.put(null, "test");

        boolean containsNull = treeMapWithNullKey.containsKey(null);

        assertTrue(containsNull);
    }
}


