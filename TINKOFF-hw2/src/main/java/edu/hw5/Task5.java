package edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    public static boolean isTheCarNumberValidForRussianFederation(String carNumber) {
        String regex = "^[АВЕКМНОРСТУХ]{1}\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(carNumber);
        return matcher.matches();
    }

    private Task5() {}
}

