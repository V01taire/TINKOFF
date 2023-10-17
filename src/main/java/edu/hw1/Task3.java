package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {

    public static boolean isNestable(int[] array1, int[] array2) {

        if (array1.length == 0 || array2.length == 0) {
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
        for (int num : array) {
            min = Math.min(min, num);
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
