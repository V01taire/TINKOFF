package edu.hw1;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {

    private Task5() {
        throw new AssertionError("Utility class, do not instantiate");
    }

    public static boolean isPalindromeDescendant(int number) {
        String numStr = String.valueOf(number);
        while (numStr.length() > 1) {
            if (isPalindrome(numStr)) {
                return true;
            }
            numStr = sumAdjacentDigits(numStr);
        }
        return false;
    }

    private static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }

    private static String sumAdjacentDigits(String str) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length() - 1; i += 2) {
            int sum = Character.getNumericValue(str.charAt(i)) + Character.getNumericValue(str.charAt(i + 1));
            result.append(sum);
        }
        return result.toString();
    }
}

