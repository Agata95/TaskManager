package com.javagda25.tasks;

import java.util.Scanner;

public class ScannerLoader {
    Scanner scanner = new Scanner(System.in);

    public String choiceFromUser() {
        System.out.println("What do you do now? (GET, PUT, POST, DELETE) \n" +
                "If you want leave this application, write QUIT.");
        String line = scanner.nextLine().toUpperCase();
        return line;
    }

    public int writeNumberToDelete() {
        System.out.println("Which number of index you want to delete?");
        int line = scanner.nextInt();
        return line;
    }

    public Task askNewTask() {
        Task newTask = new Task();
        System.out.println("Task NAME is:");
        String name = scanner.nextLine();
        newTask.setName(name);

        askAboutIsDone(newTask);

        return newTask;
    }

    private void askAboutIsDone(Task newTask) {
        System.out.println("Task is DONE? (true/false)");
        Boolean isDone;
        do {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("true")) {
                isDone = true;
                newTask.setDone(true);
            } else if (line.equalsIgnoreCase("false")) {
                isDone = true;
                newTask.setDone(false);
            } else {
                System.out.println("Wrong answere");
                isDone = false;
            }
        } while (isDone = false);
    }
}
