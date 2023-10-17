package edu.hw1;

public class Task7 {
    public int rotateLeft(int n, int shift) {
        int leftShift = shift;
        String binaryString = Integer.toBinaryString(n);
        int length = binaryString.length();
        leftShift %= length;
        String rotated = binaryString.substring(leftShift) + binaryString.substring(0, leftShift);
        return Integer.parseInt(rotated, 2);
    }

    public int rotateRight(int n, int shift) {
        int rightShift = shift;
        String binaryString = Integer.toBinaryString(n);
        int length = binaryString.length();
        rightShift %= length;
        String rotated = binaryString.substring(length - rightShift) + binaryString.substring(0, length - rightShift);
        return Integer.parseInt(rotated, 2);
    }
}


