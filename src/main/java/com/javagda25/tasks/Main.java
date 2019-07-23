package com.javagda25.tasks;

public class Main {
    // operacje CRUD (Create Read Update Delete)
    public static void main(String[] args) {
        ScannerContent scannerContent = new ScannerContent();
        Manager manager = new Manager();
        String uri = "http://194.181.116.187:16666/task";
        String line;

        System.out.println("Welcome in the application Task Manager! :)");
        do {
            line = scannerContent.choiceFromUser();
            switch (line) {
                case "GET":
                    manager.methodGet(uri).forEach(System.out::println);
                    break;
                case "PUT":
                    break;
                case "POST":
                    break;
                case "DELETE":
                    manager.methodDelete(uri);
                    break;
                default:
                    System.out.println("WRONG. TRY AGAIN.");
                    break;
            }
        } while (!line.equalsIgnoreCase("QUIT"));






//        Task taskToEdit = new Task();
//        taskToEdit.setId(20L);
//        taskToEdit.setName("E.T go home!");
//        taskToEdit.setCreationTime(LocalDateTime.now());
//        taskToEdit.setDone(true);
//
//        // zamiana obiekt -> tekst (json)
//        // marshaller - obiekt w tekst
//        String jsonTask = g.toJson(taskToEdit);
//
//        System.out.println(jsonTask);
//        request = HttpRequest
//                .newBuilder(URI.create("http://192.168.110.34:8080/task"))
//                .POST(HttpRequest.BodyPublishers.ofString(jsonTask))
//                .header("Content-Type", "application/json")
//                .build();


    }
}
