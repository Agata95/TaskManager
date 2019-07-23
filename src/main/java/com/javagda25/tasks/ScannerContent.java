package com.javagda25.tasks;

import java.util.Scanner;

public class ScannerContent {
    Scanner scanner = new Scanner(System.in);

    public String choiceFromUser() {
        System.out.println("What do you do now? (GET, PUT, POST, DELETE) \n" +
                "If you want leave this application, write QUIT.");
        String line = scanner.nextLine();
        return line;
    }

    public int writeNumberToDelete() {
        System.out.println("Which number of index you want to delete?");
        int line = scanner.nextInt();
        return line;
    }
}
