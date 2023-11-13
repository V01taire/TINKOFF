package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("LineLength")
public class Task6 {
    public static boolean isSubsequence(String s, String t) {
        String patternString = ".*" + s.chars().mapToObj(c -> (char) c + ".*").collect(StringBuilder::new, StringBuilder::append, StringBuilder::append) + ".*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(t);

        return matcher.matches();
    }

    private Task6() {}
}

