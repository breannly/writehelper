package com.writershelper.utils;

public class IdGenerator {

    private static long counter = System.currentTimeMillis();

    public static long generate() {
        long currentTime = System.currentTimeMillis();
        if (currentTime > counter) {
            counter = currentTime;
        } else {
            counter++;
        }
        return counter;
    }
}
