package edu.hw7.Task4;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {
    private static final String[] THOUSANDS = new String[]{"", "M", "MM", "MMM"};
    private static final String[] HUNDREDS = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TENS = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONES = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private static final int[] DIVIDERS = new int[]{1000, 100, 10};

    public static String convertToRoman(int number) {

        String romanNumber = "";

        romanNumber = romanNumber
            .concat(THOUSANDS[number / DIVIDERS[0]])
            .concat(HUNDREDS[(number % DIVIDERS[0]) / DIVIDERS[1]])
            .concat(TENS[(number % DIVIDERS[1]) / DIVIDERS[2]])
            .concat(ONES[number % DIVIDERS[2]]);

        return romanNumber;
    }
}
