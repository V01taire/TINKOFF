package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    private static final String SPECIAL_CHARACTERS = "~!@#$%^&*|";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("[" + Pattern.quote(SPECIAL_CHARACTERS) + "]");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private Task4() {}
}
