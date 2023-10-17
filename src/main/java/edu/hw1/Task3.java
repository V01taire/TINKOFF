package edu.hw1;

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

        int minArray1 = findMin(array1);
        int maxArray1 = findMax(array1);
        int minArray2 = findMin(array2);
        int maxArray2 = findMax(array2);

        return minArray1 > minArray2 && maxArray1 < maxArray2;
    }

    private static int findMin(int[] array) {
        int min = array[0];
        int currentMin = min;
        for (int num : array) {
            currentMin = num;
            if (currentMin < min) {
                min = currentMin;
            }
        }
        return min;
    }

    private static int findMax(int[] array) {
        int max = array[0];
        for (int num : array) {
            max = Math.max(max, num);
        }
        return max;
    }
}
