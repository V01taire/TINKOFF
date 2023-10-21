package edu.project1;

import java.util.Random;

class ConsoleDictionary implements Dictionary {
    private static final String[] WORDS = {"hello", "world", "best", "java", "junior"};

    @Override
    public String randomWord() {
        Random random = new Random();
        int index = random.nextInt(WORDS.length);
        return WORDS[index];
    }
}
