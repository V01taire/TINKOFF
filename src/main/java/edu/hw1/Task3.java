package edu.hw1;

import java.util.Arrays;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    public static boolean isNestable(int[] array1, int[] array2) {

        //NullPointerException
        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length < 1 || array2.length < 2) {
            return false;
        }

        int maxArray1 = Arrays.stream(array1).max().getAsInt();
        int minArray1 = Arrays.stream(array1).min().getAsInt();
        int minArray2 = Arrays.stream(array2).min().getAsInt();
        int maxArray2 = Arrays.stream(array2).max().getAsInt();

        return minArray1 > minArray2 && maxArray1 < maxArray2;
    }
}
