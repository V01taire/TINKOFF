package edu.hw3;

import edu.hw3.Task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    @Test
    @DisplayName("Тест частотного словаря для массива строк")
    void testFreqDictForStrings() {
        // given
        String[] arrayOfStrings = {"apple", "banana", "apple", "orange", "banana", "apple"};
        HashMap<String, Integer> expectedFrequencyMap = new HashMap<>();
        expectedFrequencyMap.put("apple", 3);
        expectedFrequencyMap.put("banana", 2);
        expectedFrequencyMap.put("orange", 1);

        // when
        HashMap<String, Integer> frequencyMap = Task3.freqDict(arrayOfStrings);

        // then
        assertEquals(expectedFrequencyMap, frequencyMap);
    }

    @Test
    @DisplayName("Тест частотного словаря для массива целых чисел")
    void testFreqDictForIntegers() {
        // given
        Integer[] arrayOfIntegers = {1, 2, 3, 2, 1, 3, 4, 5, 5};
        HashMap<Integer, Integer> expectedFrequencyMap = new HashMap<>();
        expectedFrequencyMap.put(1, 2);
        expectedFrequencyMap.put(2, 2);
        expectedFrequencyMap.put(3, 2);
        expectedFrequencyMap.put(4, 1);
        expectedFrequencyMap.put(5, 2);

        // when
        HashMap<Integer, Integer> frequencyMap = Task3.freqDict(arrayOfIntegers);

        // then
        assertEquals(expectedFrequencyMap, frequencyMap);
    }

    @Test
    @DisplayName("Тест частотного словаря для пустого массива")
    void testFreqDictForEmptyArray() {
        // given
        String[] emptyArray = new String[0];
        HashMap<String, Integer> expectedFrequencyMap = new HashMap<>();

        // when
        HashMap<String, Integer> frequencyMap = Task3.freqDict(emptyArray);

        // then
        assertEquals(expectedFrequencyMap, frequencyMap);
    }

    @Test
    @DisplayName("Тест частотного словаря для массива с одним элементом")
    void testFreqDictForSingleElementArray() {
        // given
        String[] singleElementArray = {"apple"};
        HashMap<String, Integer> expectedFrequencyMap = new HashMap<>();
        expectedFrequencyMap.put("apple", 1);

        // when
        HashMap<String, Integer> frequencyMap = Task3.freqDict(singleElementArray);

        // then
        assertEquals(expectedFrequencyMap, frequencyMap);
    }
}
