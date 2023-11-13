package edu.hw5;

public class Task7 {

    public static boolean containsAtLeast3Zeros(String input) {
        String regex = ".*0.{2,}.*";
        return input.matches(regex);
    }

    public static boolean startsAndEndsWithSameChar(String input) {
        String regex = "^([01]).*\\1$";
        return input.matches(regex);
    }

    public static boolean lengthBetween1And3(String input) {
        String regex = "^[01]{1,3}$";
        return input.matches(regex);
    }

    private Task7() {}
}

