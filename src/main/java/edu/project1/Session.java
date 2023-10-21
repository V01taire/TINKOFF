package edu.project1;

import java.util.Arrays;

class Session {
    private final String answer;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session(String answer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '_');
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }

    public GuessResult guess(char guess) {
        if (attempts == maxAttempts) {
            return new GuessResult.Defeat(answer.toCharArray());
        }

        boolean hit = false;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == guess) {
                userAnswer[i] = guess;
                hit = true;
            }
        }

        boolean isWin = Arrays.equals(userAnswer, answer.toCharArray());
        boolean isHit = hit;

        attempts++;
        return isWin
            ? new GuessResult.Win(userAnswer, attempts)
            : isHit
            ? new GuessResult.Hit(userAnswer)
            : (attempts >= maxAttempts)
            ? new GuessResult.Defeat(answer.toCharArray())
            : new GuessResult.Miss(userAnswer);
    }
}
