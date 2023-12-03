package edu.hw8.Task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static edu.hw8.Task3.Main.MAX_THREAD_COUNT;

@SuppressWarnings("MagicNumber")
public class MultiBrute {

    private final Map<String, String> dataBase;
    private final Map<String, String> outputDataBase;
    private final List<Character> alphabet;
    private CountDownLatch cdl;
    private final ReentrantReadWriteLock rwLock;
    private int counter = 0;
    private static final int HEX_BITMASK = 0xFF;

    public MultiBrute(List<String> inputDataBase) {
        this.dataBase = new ConcurrentHashMap<>();
        for (String string : inputDataBase) {
            String[] values = string.split(" ");
            this.dataBase.put(values[1], values[0]);
        }
        alphabet = new ArrayList<>();
        fillAlphabet();
        rwLock = new ReentrantReadWriteLock();
        outputDataBase = new ConcurrentHashMap<>();
        cdl = new CountDownLatch(MAX_THREAD_COUNT);
    }

    public Map<String, String> bruteDatabase(int countThreads) {
        List<Thread> threads = new ArrayList<>();
        cdl = new CountDownLatch(countThreads);
        for (int i = 0; i < countThreads; i++) {
            threads.add(new Thread(this::nextPassword));
            threads.get(i).start();
        }
        try {
            for (int i = 0; i < countThreads; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return outputDataBase;
    }


    private void nextPassword() {
        int selfIndex = getSelfIndex();
        cdl.countDown();
        try {
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int size = alphabet.size() / counter;
        int start = selfIndex == 1 ? 0 : size * (selfIndex - 1) + 1;
        int end = selfIndex == counter ? alphabet.size() - 1 : size * selfIndex;
        StringBuilder password = new StringBuilder();

        while (dataBase.size() > outputDataBase.size()) {
            do {
                getNextWord(password, start, end);
            } while (!dataBase.containsKey(md5(password.toString())) && dataBase.size() > outputDataBase.size());

            if (dataBase.containsKey(md5(password.toString()))) {
                rwLock.writeLock().lock();
                outputDataBase.put(dataBase.get(md5(password.toString())), password.toString());
                rwLock.writeLock().unlock();
            }
        }
    }

    private void getNextWord(StringBuilder string, int start, int end) {
        int index = string.length() - 1;

        if (index == -1) {
            string.append(alphabet.get(start));
            return;
        }

        if (index == 0) {
            if (string.charAt(index) == alphabet.get(end)) {
                string.setCharAt(index, alphabet.get(start));
                string.append(alphabet.get(0));
            } else {
                string.setCharAt(index, getNextChar(string.charAt(index)));
            }
        } else if (string.charAt(index) == alphabet.get(alphabet.size() - 1)) {
            while (index > 0 && string.charAt(index) == alphabet.get(alphabet.size() - 1)) {
                string.setCharAt(index, alphabet.get(0));
                index--;
            }
            if (index == 0) {
                if (string.charAt(index) == alphabet.get(end)) {
                    string.append(alphabet.get(0));
                    string.setCharAt(index, alphabet.get(start));
                } else {
                    string.setCharAt(index, getNextChar(string.charAt(index)));
                }
            } else {
                string.setCharAt(index, getNextChar(string.charAt(index)));
            }
        } else {
            string.setCharAt(index, getNextChar(string.charAt(index)));
        }
    }

    private char getNextChar(char ch) {
        return alphabet.get(alphabet.indexOf(ch) + 1);
    }

    private void fillAlphabet() {
        for (char i = 'a'; i <= 'z'; i++) {
            alphabet.add(i);
        }
        for (char i = '0'; i <= '9'; i++) {
            alphabet.add(i);
        }
    }

    private synchronized int getSelfIndex() {
        return ++counter;
    }

    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] array) {
        char[] val = new char[2 * array.length];
        String hex = "0123456789abcdef";
        for (int i = 0; i < array.length; i++) {
            int b = array[i] & HEX_BITMASK;
            val[2 * i] = hex.charAt(b >>> 4);
            val[2 * i + 1] = hex.charAt(b & 15);
        }
        return String.valueOf(val);
    }
}
