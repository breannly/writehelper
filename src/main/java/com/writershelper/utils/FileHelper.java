package com.writershelper.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    public static boolean write(String fileName, String context) {
        try (FileWriter fileWriter = new java.io.FileWriter(fileName, true)) {
            fileWriter.write(context);
            return true;
        } catch (IOException e) {
            System.err.println("ERROR: write file error");
            return false;
        }
    }

    public static boolean rewrite(String fileName, String context) {
        try (FileWriter fileWriter = new java.io.FileWriter(fileName, false)) {
            fileWriter.write(context);
            return true;
        } catch (IOException e) {
            System.err.println("ERROR: write file error");
            return false;
        }
    }


    public static<T> String read(String fileName, Long id, T t) {
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"id\":" + id)) {
                    line = JsonUtil.toJson(t);
                }
                stringBuilder.append(line).append('\n');
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: file not found error");
        } catch (IOException e) {
            System.err.println("ERROR: read file error");
        }
        return "";
    }

    public static String read(String fileName, Long id) {
        String context = "";
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("\"id\":" + id)) {
                    context = line;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: file not found error");
        } catch (IOException e) {
            System.err.println("ERROR: read file error");
        }
        return context;
    }
}
