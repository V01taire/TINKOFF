package edu.hw1;

public class Task1 {
    private static final int MINUTES_POSITION = 0;
    private static final int SECONDS_POSITION = 1;
    private static final int SECONDS_IN_MINUTE = 60;

    public int convertToSeconds(String minutesAndSeconds) {
        if (!isValidTimeFormat(minutesAndSeconds)) {
            return -1;
        }

        String[] timeParts = minutesAndSeconds.split(":");
        int minutes = Integer.parseInt(timeParts[MINUTES_POSITION]);
        int seconds = Integer.parseInt(timeParts[SECONDS_POSITION]);

        return minutes * SECONDS_IN_MINUTE + seconds;
    }

    private boolean isValidTimeFormat(String time) {
        String[] timeParts = time.split(":");
        if (timeParts.length != 2) {
            return false;
        }

        int minutes = Integer.parseInt(timeParts[MINUTES_POSITION]);
        int seconds = Integer.parseInt(timeParts[SECONDS_POSITION]);

        return minutes >= 0 && seconds >= 0 && seconds < SECONDS_IN_MINUTE;
    }
}


