package edu.hw3.Task1;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {
    private static final int ALPHABET_SIZE = 26;

    public static String atbashCipher(String originalString) {
        Map<Character, Character> lettersAndTheirReversedLetters = new HashMap<>();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            char reversedLetter = (char) ('a' + ALPHABET_SIZE - 1 - (letter - 'a'));
            lettersAndTheirReversedLetters.put(letter, reversedLetter);
        }

        StringBuilder atbashString = new StringBuilder();
        for (char c : originalString.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowercaseC = Character.toLowerCase(c);
                char reversedLetter = lettersAndTheirReversedLetters.getOrDefault(lowercaseC, c);
                if (Character.isUpperCase(c)) {
                    reversedLetter = Character.toUpperCase(reversedLetter);
                }
                atbashString.append(reversedLetter);
            } else {
                atbashString.append(c);
            }
        }

        String result = atbashString.toString();
        return result;
    }
}

