package com.IDATT2105.IDATT2105.security;

import java.security.SecureRandom;

public class SecretKeyGenerator {

    private static final int KEY_LENGTH_BYTES = 32; // 256 bits

    public static String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[KEY_LENGTH_BYTES];
        secureRandom.nextBytes(keyBytes);
        return bytesToHex(keyBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
