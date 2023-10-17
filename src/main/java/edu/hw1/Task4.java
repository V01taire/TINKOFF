package edu.hw1;

class Task4 {
    public String fixString(String brokenString) {
        char[] chars = brokenString.toCharArray();
        for (int i = 0; i < chars.length - 1; i += 2) {
            char temp = chars[i];
            chars[i] = chars[i + 1];
            chars[i + 1] = temp;
        }
        return new String(chars);
    }
}
