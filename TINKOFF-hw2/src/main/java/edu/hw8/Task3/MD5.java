package edu.hw8.Task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("HideUtilityClassConstructor")
public class MD5 {

    private static final int HEX_BITMASK = 0xFF;

    public static String md5Hash(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] array) {
        StringBuilder hexString = new StringBuilder(2 * array.length);
        for (byte b : array) {
            hexString.append(String.format("%02x", b & HEX_BITMASK));
        }
        return hexString.toString();
    }
}
