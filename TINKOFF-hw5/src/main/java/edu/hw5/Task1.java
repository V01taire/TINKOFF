package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"LineLength", "HideUtilityClassConstructor"})
public class Task1 {

    private static final String DATE_FORMAT = "yyyy-MM-dd, HH:mm";

    public static String calculateTheAverageTimePerSession(List<String> timestamps) {
        Duration totalDuration = Duration.ZERO;

        for (String timestamp : timestamps) {
            Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");
            Matcher matcher = pattern.matcher(timestamp);

            if (matcher.find()) {
                String startStr = matcher.group(1);
                String endStr = matcher.group(2);

                LocalDateTime startTime = LocalDateTime.parse(startStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
                LocalDateTime endTime = LocalDateTime.parse(endStr, DateTimeFormatter.ofPattern(DATE_FORMAT));

                Duration sessionDuration = Duration.between(startTime, endTime);
                totalDuration = totalDuration.plus(sessionDuration);
            }
        }

        return formatDuration(totalDuration.dividedBy(timestamps.size()));
    }

    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();

        return hours + "ч " + minutes + "м";
    }
}
