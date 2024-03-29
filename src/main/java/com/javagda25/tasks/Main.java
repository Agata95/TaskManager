package com.javagda25.tasks;

import java.util.Optional;

public class Main {
    // operacje CRUD (Create Read Update Delete)
    public static void main(String[] args) {
        ScannerLoader scannerLoader = new ScannerLoader();
        Manager manager = new Manager();
        String uri = "http://194.181.116.187:16666/task";
        String line;

        System.out.println("Welcome in the application Task Manager! :)");
        do {
            line = scannerLoader.choiceFromUser();
            switch (line) {
                case "GET":
                    manager.methodGet(uri).forEach(System.out::println);
                    break;
                case "PUT":
                    manager.methodPut(uri, scannerLoader.askNewTask());
                    break;
                case "POST":
                    Optional<Task> taskToUpdateOptional = manager.getById(uri,scannerLoader.askIDToGet());
                    if (taskToUpdateOptional.isPresent()) {
                        Task taskToEdit = taskToUpdateOptional.get();
                        manager.methodPost(uri, scannerLoader.taskToUpdate(taskToEdit));
                    }
                    break;
                case "DELETE":
                    manager.methodDelete(uri);
                    break;
                default:
                    if (!line.equalsIgnoreCase("quit")) {
                        System.out.println("WRONG. TRY AGAIN.");
                    }
                    break;
            }
        } while (!line.equalsIgnoreCase("QUIT"));
    }
}
