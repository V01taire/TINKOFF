package edu;


public class Task8 {

    public static boolean isOddLength(String input) {
        String regex = "^([0|1]{2})*[0|1]$";
        return input.matches(regex);
    }

    public static boolean startsWith0AndHasOddLengthOrStartsWith1AndHasEvenLength(String input) {
        String regex = "^(0([0|1]{2})*)$|^(1([0|1]{2})*[0|1])$";
        return input.matches(regex);
    }

    public static boolean countOf0IsMultipleOf3(String input) {
        String regex = "^((1*01*){3})+$";
        return input.matches(regex);
    }

    public static boolean anyStringExcept11Or111(String input) {
        String regex = "^(?!11$|111$)[01]+$";
        return input.matches(regex);
    }

    public static boolean everyOddCharacterIs1(String input) {
        String regex = "^(1[0|1]?)+1?$";
        return input.matches(regex);
    }

    public static boolean atLeastTwo0AndAtMostOne1(String input) {
        String regex = "^(?=(.*0){2})(?!.*1.*1)[01]+$";
        return input.matches(regex);
    }

    public static boolean noConsecutive1(String input) {
        String regex = "^(?!.*11)[01]+$";
        return input.matches(regex);
    }

    private Task8() {}
}

