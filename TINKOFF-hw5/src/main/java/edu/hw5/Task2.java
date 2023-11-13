package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    private static final int THIRTEEN = 13;
    private static final int TWELVE_MONTHS = 12;
    private static final int THE_THIRTEENTH = 13;

    public static String findFridayThe13ths(int year) {
        List<LocalDate> fridayThe13ths = new ArrayList<>();

        for (int month = 1; month <= TWELVE_MONTHS; month++) {
            LocalDate date = LocalDate.of(year, month, THE_THIRTEENTH);

            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridayThe13ths.add(date);
            }
        }

        return fridayThe13ths.toString();
    }

    public static LocalDate findNextFriday13th(LocalDate date) {
        TemporalAdjuster getFridayThe13h = TemporalAdjusters.ofDateAdjuster(localDate -> {
            LocalDate temp = localDate.withDayOfMonth(THIRTEEN);
            while (temp.getDayOfWeek() != DayOfWeek.FRIDAY) {
                temp = temp.plusMonths(1);
            }
            return temp;
        });

        return date.with(getFridayThe13h);
    }
}
