package edu.hw10.Task1Test;

import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {

    @Test
    void testNextObjectForClassWithoutFactoryMethod() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        MyClass myClass = rog.nextObject(MyClass.class);
        assertNotNull(myClass);
    }

    @Test
    void testNextObjectForClassWithFactoryMethod() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        MyClass myClass = rog.nextObject(MyClass.class, "create");
        assertNotNull(myClass);
    }

    @Test
    void testGenerateRandomValueForInt() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Object value = rog.generateRandomValue(int.class);
        assertTrue(value instanceof Integer);
    }

    @Test
    void testGenerateRandomValueForDouble() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Object value = rog.generateRandomValue(double.class);
        assertTrue(value instanceof Double);
    }

    @Test
    void testGenerateRandomValueForString() {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        Object value = rog.generateRandomValue(String.class);
        assertTrue(value instanceof String);
    }
}

