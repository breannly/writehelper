package com.writershelper.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static String read() throws IOException {
        return reader.readLine();
    }
}
