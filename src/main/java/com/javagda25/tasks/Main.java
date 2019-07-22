package com.javagda25.tasks;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

public class Main {
    // operacje CRUD (Create Read Update Delete)
    public static void main(String[] args) {
        ScannerContent scannerContent = new ScannerContent();
        Tasks tasks = new Tasks();
        String line = scannerContent.choiceFromUser();

        do {
            switch (line) {
                case "GET":
                    tasks.methodGet();
                    break;
                case "PUT":
                    break;
                case "POST":
                    break;
                case "DELETE":
                    tasks.methodDelete();
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
