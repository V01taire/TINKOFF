package edu.hw1;

import java.util.Arrays;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    public static boolean isNestable(int[] array1, int[] array2) {
        if (array1.length == 0 || array2.length == 0) {
            return false;
        }

        int minArray1 = Arrays.stream(array1).min().orElse(Integer.MAX_VALUE);
        int maxArray1 = Arrays.stream(array1).max().orElse(Integer.MIN_VALUE);
        int minArray2 = Arrays.stream(array2).min().orElse(Integer.MAX_VALUE);
        int maxArray2 = Arrays.stream(array2).max().orElse(Integer.MIN_VALUE);

        return minArray1 > minArray2 && maxArray1 < maxArray2;
    }
}

