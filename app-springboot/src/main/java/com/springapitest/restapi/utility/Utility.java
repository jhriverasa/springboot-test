package com.springapitest.restapi.utility;

public class Utility {

    public static String generateStringDigits(int size) {
        char chars[] = new char[size];
        for (int i = 0; i < size; i++) {
            int randDigit = (int) Math.floor(Math.random() * 10); // generates a digit (0-9)
            chars[i] = (Integer.toString(randDigit).charAt(0));
        }
        return new String(chars);
    }
}
