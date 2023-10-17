package edu.hw1;

import java.util.Arrays;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {

    private Task6() {
        throw new AssertionError("Utility class, do not instantiate");
    }

    public static int countK(int number) {
        final int TARGET = 6174;
        if (number == TARGET) {
            return 1;
        }

        char[] digits = Integer.toString(number).toCharArray();
        Arrays.sort(digits);
        int ascending = Integer.parseInt(new String(digits));

        // Инвертируем цифры для убывающего порядка
        StringBuilder sb = new StringBuilder(new String(digits));
        int descending = Integer.parseInt(sb.reverse().toString());

        int difference = descending - ascending;

        if (difference == TARGET) {
            return 1;
        } else {
            return 1 + countK(difference);
        }
    }
}
