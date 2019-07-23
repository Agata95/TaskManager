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

    public Task askNewTask() {
        Task newTask = new Task();
        System.out.println("Task NAME is:");
        String name = scanner.nextLine();
        newTask.setName(name);

        askAboutIsDone(newTask);

        return newTask;
    }

    public Task taskToUpdate(Task taskToEdit) {
        System.out.println("New task NAME is:");
        String name = scanner.nextLine();
        taskToEdit.setName(name);

        askAboutIsDone(taskToEdit);

        return taskToEdit;
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

    public Long askId(String message) {
        Long id = null;
        do {
            try {
                System.out.println(message);
                id = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.err.println("WRONG!");
            }
        } while (id == null);
        return id;
    }

    public Long askIDToGet() {
        return askId("Task ID to update is:");
    }

    public Long askIDToDelete() {
        return askId("Which number of index you want to delete?");
    }
}
