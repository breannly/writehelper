package com.writershelper.view.common;

import com.google.common.base.Throwables;
import com.writershelper.ApplicationManager;
import com.writershelper.utils.ConsoleReader;
import com.writershelper.view.label.LabelView;
import com.writershelper.view.post.PostView;
import com.writershelper.view.writer.WriterView;

public class CommonView {

    public static void run() {
        WriterView writerView = ApplicationManager.getWriterView();
        PostView postView = ApplicationManager.getPostView();
        LabelView labelView = ApplicationManager.getLabelView();
        boolean isRunning = true;

        System.out.println("Welcome to the WriterHelper!");
        while (isRunning) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Manage Writers");
            System.out.println("2. Manage Posts");
            System.out.println("3. Manage Labels");
            System.out.println("4. Exit");

            try {
                int choice = Integer.parseInt(ConsoleReader.read());

                switch (choice) {
                    case 1 -> runWriterMenu(writerView);
                    case 2 -> runPostMenu(postView);
                    case 3 -> runLabelMenu(labelView);
                    case 4 -> {
                        isRunning = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please choose again.");
                }
            } catch (Exception e) {
                System.err.println(Throwables.getStackTraceAsString(e));
                System.err.println("Something got wrong... Try again!");
            }
        }
    }

    private static void runWriterMenu(WriterView writerView) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Create writer");
            System.out.println("2. Get writer by id");
            System.out.println("3. Update writer");
            System.out.println("4. Delete writer");
            System.out.println("5. Exit");

            try {
                int choice = Integer.parseInt(ConsoleReader.read());

                switch (choice) {
                    case 1 -> writerView.create();
                    case 2 -> writerView.get();
                    case 3 -> writerView.update();
                    case 4 -> writerView.delete();
                    case 5 -> isRunning = false;
                    default -> System.out.println("Invalid choice. Please choose again.");
                }
            } catch (Exception e) {
                System.out.println(Throwables.getStackTraceAsString(e));
                System.err.println("Something got wrong... Try again!");
            }
        }
    }

    private static void runPostMenu(PostView postView) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Create post");
            System.out.println("2. Get post by id");
            System.out.println("3. Update post");
            System.out.println("4. Delete post");
            System.out.println("5. Exit");

            try {
                int choice = Integer.parseInt(ConsoleReader.read());

                switch (choice) {
                    case 1 -> postView.create();
                    case 2 -> postView.get();
                    case 3 -> postView.update();
                    case 4 -> postView.delete();
                    case 5 -> isRunning = false;
                    default -> System.out.println("Invalid choice. Please choose again.");
                }
            } catch (Exception e) {
                System.err.println(Throwables.getStackTraceAsString(e));
                System.err.println("Something got wrong... Try again!");
            }
        }
    }

    private static void runLabelMenu(LabelView labelView) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Create label");
            System.out.println("2. Get label by id");
            System.out.println("3. Update label");
            System.out.println("4. Delete label");
            System.out.println("5. Exit");

            try {
                int choice = Integer.parseInt(ConsoleReader.read());

                switch (choice) {
                    case 1 -> labelView.create();
                    case 2 -> labelView.get();
                    case 3 -> labelView.update();
                    case 4 -> labelView.delete();
                    case 5 -> isRunning = false;
                    default -> System.out.println("Invalid choice. Please choose again.");
                }
            } catch (Exception e) {
                System.err.println("Something got wrong... Try again!");
            }
        }
    }
}
