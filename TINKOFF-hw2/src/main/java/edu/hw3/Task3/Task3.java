package edu.hw3.Task3;

import java.util.HashMap;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {
    public static <T> HashMap<T, Integer> freqDict(T[] arrayOfObjects) {
        HashMap<T, Integer> frequencyMap = new HashMap<>();
        for (T object : arrayOfObjects) {
            frequencyMap.put(object, frequencyMap.getOrDefault(object, 0) + 1);
        }
        return frequencyMap;
    }
}
