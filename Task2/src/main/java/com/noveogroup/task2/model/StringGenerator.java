package com.noveogroup.task2.model;

import java.util.Random;

/**
 * Created by Arseny on 26.07.2014.
 */
public class StringGenerator {
    public static String generateString(int length) {
        Random rng = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
