package com.project.fumble.config;

import java.util.Random;

public class UserIdGenerator {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 6; // Length of the random part
    private static final String PREFIX = "USR-"; // Prefix for userId

    public static String generateUserId() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < ID_LENGTH; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return PREFIX + builder.toString();
    }
}